package com.example.poupixterminal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.paxbr.easypaymentpos.POSConfig;
import br.com.paxbr.easypaymentpos.callback.CallBackUser;
import br.com.paxbr.easypaymentpos.common.ReceiptEnum;
import br.com.paxbr.easypaymentpos.common.Status;
import br.com.paxbr.easypaymentpos.controller.CancellationPOS;
import br.com.paxbr.easypaymentpos.controller.ReceiptPOS;
import br.com.paxbr.easypaymentpos.controller.TransactionPOS;
import br.com.paxbr.easypaymentpos.controller.TransactionPOSKt;
import br.com.paxbr.easypaymentpos.database.AppDatabase;
import br.com.paxbr.easypaymentpos.domain.Receipt;
import br.com.paxbr.easypaymentpos.domain.ReceiptContent;
import br.com.paxbr.easypaymentpos.domain.TransactionResult;
import br.com.paxbr.easypaymentpos.domain.User;
import br.com.paxbr.easypaymentpos.util.SharedPreferencesUtilKt;
import br.com.setis.bibliotecapinpad.definicoes.Menu;

import static br.com.paxbr.easypaymentpos.common.Constants.DATABASE_NAME;

public class CancelationActivity extends AppCompatActivity implements CallBackUser<Object> {
    CancelationScreenStatus cancelationScreenStatus;
    POSConfig config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelation);
        cancelationScreenStatus = new CancelationScreenStatus(this);
    }

    private void cancel() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase database = Room.databaseBuilder(CancelationActivity.this, AppDatabase.class, DATABASE_NAME).build();
                final List<TransactionResult> transactions =  database.transactionDao().getAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        config = POSConfig.Companion.getInstance();
                        config.register(CancelationActivity.this);
                        User user = new User("42988901805","https://137.135.69.200:1447");
                        config.initialize(CancelationActivity.this, user, new CallBackUser<Status>() {
                            @Override
                            public void onRequest(Status status) {
                                if(status == Status.APPROVED){
                                    final CancellationPOS cancellation = new CancellationPOS(CancelationActivity.this, new TransactionResult("no_tid"));
                                    //Existem duas formas de cancelar uma transação, sabendo a transação ou listando todas para um cartão
                                    //Para listar, chame esse metodo:
                                    cancellation.startValidTransaction(new CallBackUser<Status>() {
                                        //Caso ja saiba a transação, chame cancellation.start
                                        @Override
                                        public void onRequest(Status status) {
                                            if(status == Status.APPROVED){
                                                ReceiptPOS receiptPOS = new ReceiptPOS(CancelationActivity.this);
                                                ReceiptContent receiptContent = TransactionPOSKt.toObject(SharedPreferencesUtilKt.loadString("", CancelationActivity.this, ReceiptEnum.CANCELLATION.toString()));
                                                List<Receipt> receipts = receiptContent.receipts;
                                                //receipts[0] = via do estabelecimento (já impressa pela lib)
                                                //receipts[1] = via do cliente
                                                receiptPOS.print(receipts.get(1), new CallBackUser<Status>() {
                                                    @Override
                                                    public void onRequest(Status status) {
                                                        finish();
                                                    }
                                                });
                                            }else {
                                                Toast.makeText(CancelationActivity.this,"Cancellation error 0",Toast.LENGTH_SHORT).show();
                                                finish();
                                            }
                                        }
                                    });
                                }else {
                                    finish();
                                    Toast.makeText(CancelationActivity.this,"Configuration error",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        }).start();
    }

    @Override
    public void onRequest(final Object object) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (object instanceof TransactionPOS.POSObject) {
                    cancelationScreenStatus.hideAllScreens();
                    TransactionPOS.POSObject posObject = (TransactionPOS.POSObject) object;
                    if(posObject.getPosInteraction() == TransactionPOS.POSInteraction.SHOW_MESSAGE || posObject.getPosInteraction() == TransactionPOS.POSInteraction.CARD_REQUEST){
                        cancelationScreenStatus.showRandomText(posObject.toString());
                    }else if( posObject.getPosInteraction() == TransactionPOS.POSInteraction.SELECT_TRANSACTION){
                        List<TransactionResult> transactionsFilter = new ArrayList<>((List<TransactionResult>)posObject.getAny());
                        //Liste as transações para que o usuário selecione qual deseja cancelar, caso já saiba, busque ela pelo id e devolva opara lib
                        config.response(transactionsFilter.get(0));
                    }else if(posObject.getPosInteraction() == TransactionPOS.POSInteraction.SELECT_APPLICATION){
                        //Exiba pra que o usuário selecione o tipo de aplicação
                        Menu menu = (Menu) posObject.getAny();
                        List<String> applications = menu.obtemOpcoesMenu();
                        cancelationScreenStatus.showApplicationsScreen(applications, config);
                        config.select(1);//Exemplo de aplicacao selecionada
                    }
                }
            }
        });

    }

}