# ooweatLibrary
```
개인프로젝트를 진행하며 자주 사용하거나 편의성을 위한 기능을 구현한다.
```
##*Util
###[PathUtil]
```
Request Type : None
Return Type : String
```
`className` : 실행된 구문의 package 부터 class 까지의 경로

`methodName` : 실행된 구문의 method 명 

`classWithMethodName` : 실행된 구문의 method 까지의 package.class.method 명
##
###[LogUtil]
```
Request Type : None
Return Type : String
```
`now` : 현재 시간의 format
##
###[DateUtil]
```
Request Type : None
Return Type : String
```
`sysdate` : yyyy-MM-dd HH:mm:ss

`yyyyMMdd` : yyyyMMdd

`yyyyMMddWithHypen` : yyyy-MM-dd

`yyyyMMddWithSlash` : yyyy/MM/dd

`hhmmss` : HHmmss

`hhmmssWithColon` : HH:mm:ss
##
###[StringUtil]
```
Request Type : String[]
Return Type : Boolean
```
`in` : 해당 문자들을 포함하는지

`notIn` : 해당 문자들이 포함되어 있지 않은지

##
##*SMTP
```
Request Type : Map<String, String>
Return Type : String
```
`gmail` : G-Mail SMTP 전송

##Entity
###[SmtpBody]
```
String sender;		//발신자
String nickName;	//별명
String password;	//Gmail에서 2차 인증으로 암호화된 비밀번호
String receiver;	//수신자
String title;		//제목
String content;		//내용
```
