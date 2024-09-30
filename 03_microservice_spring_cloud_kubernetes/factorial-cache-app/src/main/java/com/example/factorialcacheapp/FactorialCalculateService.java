package com.example.factorialcacheapp;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;

@Service
public class FactorialCalculateService {

    // HTTP client
    private final RestClient factorialClient = RestClient.create();

    public BigDecimal getCalculateResult(int n) {

        /* 
            HTTP client 를 이용해서 factorial-app-service:8080/factorial?n=10 호출해서 결과를 받을수 있도록 구현
             => 두 pod
                 - my-factorial-app~~~
                 - my-factorial-cache-app~~~
             는 모두 동일한 factorial namespace 에 있고, 동일할 네트워크 상에 있으므로
             K8S Service 객체명 (type : Cluster IP ) 으로 접속이 가능함
         */
        String result = factorialClient.get()
                // K8S 의 Service (ClusterIP) 를 이용해서 factorial-app 을 사용
                .uri("http://factorial-server-service:7080/factorial?n=" + n)
                // 호출한 결과를 받아옴
                .retrieve()
                // request 에 대한 결과에 에러가 발생했다면,
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    throw new RuntimeException("invalid server response " + response.getStatusText());
                })
                // request 한 결과를 변환해줄 클래스 설정
                .body(String.class);

        return new BigDecimal(result);
    }

}
