package com.example.poupixterminal;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import br.com.paxbr.easypaymentpos.POSConfig;
import br.com.paxbr.easypaymentpos.callback.CallBackUser;
import br.com.paxbr.easypaymentpos.common.ProductFormatKt;
import br.com.paxbr.easypaymentpos.common.ReceiptEnum;
import br.com.paxbr.easypaymentpos.common.Status;
import br.com.paxbr.easypaymentpos.controller.ReceiptPOS;
import br.com.paxbr.easypaymentpos.controller.TransactionPOS;
import br.com.paxbr.easypaymentpos.controller.TransactionPOSKt;
import br.com.paxbr.easypaymentpos.domain.InstalmentsRange;
import br.com.paxbr.easypaymentpos.domain.Product;
import br.com.paxbr.easypaymentpos.domain.ReceiptContent;
import br.com.paxbr.easypaymentpos.domain.ResponseEnum;
import br.com.paxbr.easypaymentpos.domain.TransactionInformation;
import br.com.paxbr.easypaymentpos.domain.TransactionResult;
import br.com.paxbr.easypaymentpos.domain.TypeOfTransactionEnum;
import br.com.paxbr.easypaymentpos.domain.User;
import br.com.paxbr.easypaymentpos.util.SharedPreferencesUtilKt;
import br.com.setis.bibliotecapinpad.definicoes.Menu;

public class CardInsert extends AppCompatActivity implements CallBackUser<Object> {
    private PaymentInformations paymentInformations;
    private UserInfos userInfos;
    private double storeCashback;
    private boolean hasReturned = false;
    ScreenStatus screenStatus;
    BonusValueCalculator bonusValueCalculator;
    public Product productChosen;
    public POSConfig config;

    public void setHasReturned(boolean hasReturned) {
        this.hasReturned = hasReturned;
    }

    public PaymentInformations getPaymentInformations() {
        return paymentInformations;
    }

    public void hideCardInfo(){
        ImageView cardImage = (ImageView) findViewById(R.id.payCardImage);
        cardImage.setVisibility(View.INVISIBLE);
        TextView insertText = (TextView) findViewById(R.id.MethodChoose);
        insertText.setVisibility(View.INVISIBLE);
    }

    @Override//Disabling back button
    public void onBackPressed() { }

    private View.OnClickListener backButtonOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(CardInsert.this, CpfDefine.class);
            intent.putExtra("PaymentInfo",getPaymentInformations());
            setHasReturned(true);
            startActivity(intent);
        }
    };

    private void transact() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                config = POSConfig.Companion.getInstance();
                User user = new User("42988901805", "https://137.135.69.200:1447");
                config.initialize(CardInsert.this, user, new CallBackUser<Status>() {
                    @Override
                    public void onRequest(Status status) {
                        config.register(CardInsert.this);
                        final boolean splitMode = getIntent().getBooleanExtra("split", false);
                        TransactionInformation transactionInformation = new TransactionInformation(new Locale("pt", "BR"), splitMode);//Sua localidade
                        final TransactionPOS transaction = new TransactionPOS(CardInsert.this, transactionInformation);
                        transaction.start(new CallBackUser<Status>() {
                            /**
                             * Resultado final de uma transacao
                             *
                             * */
                            @Override
                            public void onRequest(Status status) {
                                if (status == Status.APPROVED) {
                                    TransactionResult transactionResult = transaction.getTransactionResult();//Informacoes da ultima transacao
                                    assert transactionResult != null;
                                    Toast.makeText(CardInsert.this, "Transaction succeed TID:" + transactionResult.getTid(), Toast.LENGTH_SHORT).show();
                                    ReceiptPOS receiptPOS = new ReceiptPOS(CardInsert.this);
                                    ReceiptContent receiptContent = TransactionPOSKt.toObject(SharedPreferencesUtilKt.loadString("", CardInsert.this, ReceiptEnum.TRANSACTION.toString()));
                                    //receipts[0] = via do estabelecimento (já impressa pela lib)
                                    //receipts[1] = via do cliente
                                    receiptPOS.print(receiptContent.receipts.get(1), new CallBackUser<Status>() {
                                        @Override
                                        public void onRequest(Status status) {
                                            if (splitMode) {

                                            }
                                            finish();
                                        }
                                    });
                                } else {
                                    TransactionResult transactionResult = transaction.getTransactionResult();
                                    Toast.makeText(CardInsert.this, "Transaction error " + transactionResult, Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        });

                    }
                });
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_insert);
        screenStatus = new ScreenStatus(this);
        setTitle("");
        Intent intent = getIntent();
        this.paymentInformations = intent.getParcelableExtra("PaymentInfo");//Getting the payment info
        this.userInfos = intent.getParcelableExtra("UserInfo");//Getting the user info
        this.storeCashback = intent.getDoubleExtra("StoreCashback",0);//Getting the store cashback info
        String userName = userInfos.getName();
        TextView greetingText = (TextView) findViewById(R.id.greetingText);
        greetingText.setText("Olá, " + userName);
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(backButtonOnClick);
        bonusValueCalculator = new BonusValueCalculator(paymentInformations.getValue(),this.storeCashback, this.userInfos);
        bonusValueCalculator.calculateBonus();
        productChosen = new Product();
        transact(); //Incializando a transação
    }

    public void onRequest(final Object o) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (o instanceof TransactionPOS.POSObject) {
                    TransactionPOS.POSObject posObject = (TransactionPOS.POSObject) o;
                    screenStatus.hideAllScreens();
                    switch (posObject.getPosInteraction()) {
                        case INSERT_AMOUNT:// Deve ser retornado o valor para a lib
                            String value = bonusValueCalculator.getTotalValue().replace(",","");
                            long valueInCash = Long.parseLong(value.substring(3));
                            Toast.makeText(CardInsert.this, "" + valueInCash, Toast.LENGTH_SHORT).show();
                            config.response(valueInCash);
                            break;
                        case CARD_REQUEST: //Deve ser exibido ao usuário uma mensagem solicitando o cartão
                            screenStatus.showCardInfo();
                            break;
                        case SHOW_MESSAGE://Deve ser exibido o valor do objeto para o usuário
                            if(posObject.toString().equals("Processando...") || posObject.toString().equals("Atualizando tabelas")){
                                screenStatus.showProcessingText();
                            }else if(posObject.toString().equals("AGUARDE A SENHA")){
                                screenStatus.showPasswordScreen(bonusValueCalculator.getTotalValue(), bonusValueCalculator.getCashbackValue(), bonusValueCalculator.getMicroInvestingValue());
                            }else{
                                screenStatus.showRandomText(posObject.toString());
                            }
                            break;
                        case SENDING_TRANSACTION:
                            screenStatus.showProcessingText();
                        case PROCESSING:
                            screenStatus.showProcessingText();
                        case SELECT_PRODUCT://Exibir para o Usuario os produtos disponiveis para o seu cartao e terminal
                            final List<Product> products = (List<Product>) posObject.getAny();
                            Set<TypeOfTransactionEnum> types = ProductFormatKt.types(products);
                            final List<Product> products1 =  ProductFormatKt.filterType(products, types.iterator().next());
                            ListView l = (ListView) findViewById(R.id.cardPaymentMethods);
                            TextView methodText = findViewById(R.id.MethodChoose);
                            methodText.setText("Escolha seu método de pagamento");
                            methodText.setVisibility(View.VISIBLE);
                            screenStatus.showMethodsScreen();
                            CustomizedMethodAdapter customizedMethodAdapter = new CustomizedMethodAdapter(CardInsert.this, R.layout.custom_list, products, config, screenStatus, bonusValueCalculator);
                            l.setAdapter(customizedMethodAdapter);
                            l.setVisibility(View.VISIBLE);
                            break;
                        case SELECT_APPLICATION://Exibir para o usuario as opcoes de aplicacoes fornecidas pelo cartao, como ['Credito','Debito']
                            Menu menu = (Menu) posObject.getAny();
                            List<String> applications = menu.obtemOpcoesMenu();
                            //Toast.makeText(CardInsert.this, posObject.getPosInteraction().toString(), Toast.LENGTH_SHORT).show();
                            screenStatus.showApplicationsScreen(applications, config);
                            break;
                        case REQUEST_EXPIRATION_DATE:
                        case OPERATION_REJECTED:
                            screenStatus.showRejectedScreen();
                            break;
                        case OPERATION_APPROVED: //Deve ser exibido ao usuário o a string do objeto
                            screenStatus.showApprovedScreen();
                            //Toast.makeText(CardInsert.this, posObject.getAny().toString(), Toast.LENGTH_SHORT).show();
                            break;
                        case REMOVE_CARD:
                            screenStatus.showCardInfo();
                        case PRINT_RECEIPT://O usuario deve ser informado de um erro na impressão, geralmente associado a falta de papel
                            config.response(ResponseEnum.OK); //Quando o problema for soluciondo, envie um feedback a lib para continuar o processo transacional
                            break;
                        case INSTALLMENTS: //Deve ser solicitado ao usuário a quantidade de parcelas
                            InstalmentsRange range = (InstalmentsRange) posObject.getAny(); //minimo e maximo de parcelas permitido
                            range.getRange().getMin();//Min installment range
                            Intent intent = new Intent(CardInsert.this,PaymentParcelling.class);
                            intent.putExtra("maxParcel",Integer.parseInt(range.getRange().getMax()));
                            intent.putExtra("minParcel",Integer.parseInt(range.getRange().getMin()));
                            intent.putExtra("PaymentValue", bonusValueCalculator.getTotalValue());
                            startActivityForResult(intent, 2);
                            break;
                        default://Os outros enums sao enviados afim de notificar a aplicacao em qual passo da transacao a lib se encontra
                            Toast.makeText(CardInsert.this, posObject.getPosInteraction().toString(), Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (2) : {
                if (resultCode == Activity.RESULT_OK) {
                    config.response(data.getStringExtra("Parcells"));
                }
                break;
            }
        }
    }

}