<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker3.min.css" rel="stylesheet">
    <style>
        body {
            padding-top: 70px;
            margin: 0 auto;
        }

        .form-center {
            text-align: center;
            padding-top: 10px;
        }
    </style>
</head>

<body ng-app="matchApp" class="container-fluid" ng-controller="matchController">
	<form action="/spider-web/manualMatch/add" method="get" dir="ltr">
		europeId<input id="europeId" name="europeId"  ype="text"/>
		uniqueId<input id="uniqueId" name="uniqueId" type="text"/>
		w500Id<input id="w500Id" name="w500Id" type="text"/>
		<input value="add" type="submit"><br>
	</form>
	<h2>             </h2>
	<form action="/spider-web/manualMatch/delete" method="get" dir="ltr">
		europeId<input id="europeId" name="europeId"  ype="text"/>
		uniqueId<input id="uniqueId" name="uniqueId" type="text"/>
		w500Id<input id="w500Id" name="w500Id" type="text"/>
		<input value="delete" type="submit"><br>
	</form>
</body>

</html>