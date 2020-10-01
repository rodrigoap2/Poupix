package poupix.common.transport.http.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreationResponse {
  String id;
}
