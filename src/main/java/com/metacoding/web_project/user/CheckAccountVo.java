package com.metacoding.web_project.user;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CheckAccountVo {
  private final String impKey = "4016550234072628";
  private final String impSecret = "t0OpW62i9y4n7KVkGT1DU4OmYTWtXdWQIo4RdnIZhjdXpzbcDX2MHBCej3RgR2AxfJ4f2As7SU3tfwsy";
  private final String tokenUrl = "https://api.iamport.kr/users/getToken";
  private final String getNameUrl = "https://api.iamport.kr/vbanks/holder";
}
