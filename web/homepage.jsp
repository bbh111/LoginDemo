<%--
  Created by IntelliJ IDEA.
  User: hieu
  Date: 27/05/2020
  Time: 09:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-2-md">
            <div class="mt-5">
                <div class="dropdown" onchange="">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ${mapLang['lang']}
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="/trang-chu?lang=en">${mapLang['langOption1']}</a>
                        <a class="dropdown-item" href="/trang-chu?lang=vi">${mapLang['langOption2']}</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-10-md">
            <div class="mt-5 ml-3">
                <div class="">
                    ${mapLang['welcome']}: ${sessionScope.userSession.username}
                </div>
               <div>
                   ${mapLang['fullname']} : ${sessionScope.userSession.accountInformation.fullName}
               </div>
                <div>
                    ${mapLang['phone']} : ${sessionScope.userSession.accountInformation.phone}
                </div>
                <div>
                    ${mapLang['email']} : ${sessionScope.userSession.accountInformation.email}
                </div>
                <div>
                    ${mapLang['address']} : ${sessionScope.userSession.accountInformation.address}
                </div>
                <div>
                    ${mapLang['intro']} : ${sessionScope.userSession.accountInformation.introduction}
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
