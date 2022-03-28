package com.cos.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T> { //제네릭 T를 사용하는 이유는 데이에 int, string등 타입이 달라질 수 있기 때문에 유동적으로 사용하기 위해서
	int status;
	T data;
}
