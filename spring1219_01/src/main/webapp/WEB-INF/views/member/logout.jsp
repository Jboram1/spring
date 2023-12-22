<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <title>Title</title>
    <style>
        *{margin: 0;padding: 0; }
        body{position: relative; height: 100%;background: #F5F6F7;}
        #wrap{position: relative;min-height: 100%;font-size: 12px}
        #header{position: relative;box-sizing: border-box; height: 168px;padding: 62px 0 0;width: 768px;margin: 0 auto;}
        #h_logo{display: block;overflow: hidden; width: 231px;height: 44px;margin: 0 auto;font-size: 15px;color: transparent;background: url("https://static.nid.naver.com/images/ui/login/pc_sp_login_190522.png") no-repeat -1px -1px;}
        #container{padding-bottom: 50px;min-height: 185px;}
        #content{ width: 460px;margin: 0 auto;padding-bottom: 30px;}
        .id_area{position: relative;}
        .input_row{position: relative;height: 29px;margin: 0 0 14px;padding: 10px 35px 10px 15px;border: solid 1px #DADADA;background: #fff;}
        .input_box{display: block; overflow: hidden;}
        .int{ font-size: 15px;line-height: 16px; position: relative;z-index: 9;width: 100%;height: 16px;padding: 7px 0 6px;color: #000;border: none;background: #fff;appearance: none;}
        .btn_login{display: block;width: 100%;height: 61px;margin:30px 0 14px;padding-top: 1px;border: none;background: #03C75A;text-align: center; color: #fff;font-size: 20px;font-weight: 700;line-height: 61px;cursor: pointer;}
    </style>


</head>
<body>
	<script>
			alert("로그아웃 되었습니다.");
			location.href="/";
	</script>
</body>
</html>