<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>coffecode.org</title>
    <meta name="description"
          content="Free open source projects present different java solutions using spring, hibernate and other popular frameworks.">
    <meta name="keywords"
          content="java, spring, hibernate, apache, tomcat, coding, programmer, linux, google cloud platform, open source, bootstrap, mysql, java ideas">
    <!-- CSS dependencies -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
          type="text/css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.standalone.min.css">
    <link rel="stylesheet" href="resources/css/now-ui-kit.css" type="text/css">
    <link rel="icon" href="resources/img/favicon.png">
    <!-- PAGE scripts -->
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>
    <script src="resources/js/functions.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.js"></script>
</head>
<body ng-app="FtpManagement" ng-controller="FtpManagementController" class="bg-light text-dark" style="">
<div class="collapse" id="navbarHeader">
    <div class="container">
        <div class="row">
            <div class="col-md-7 py-4">
                <h4>About</h4>
                <p class="text-info">Free open source projects present different java solutions using spring, hibernate
                    and other popular frameworks.</p>
            </div>
            <div class="col-md-3 offset-md-1 py-4">
                <h4>Contact</h4>
                <ul class="list-unstyled">
                    <li>
                        <a href="https://pl.linkedin.com/in/michalsiwiak" class="text-secondary" target="_blank">Follow
                            on LinkedIn</a>
                    </li>
                    <li>
                        <a href="mailto:info@coffecode.org" target="_top" class="text-secondary">Email me</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="navbar sticky-top navbar-dark bg-info">
    <div class="container d-flex justify-content-between">
        <a href="https://www.coffecode.org/" class="navbar-brand d-flex align-items-center"><i
                class="fa fa-home fa-2x lead fa-fw d-inline-block" aria-hidden="true"></i>&nbsp;&nbsp;<text class="">
            HOME
        </text>
        </a>
        <a href="#" class="navbar-brand d-flex align-items-center"><i
                class="fa fa-git-square fa-fw d-inline-block lead fa-2x"></i>&nbsp;&nbsp;<text class="">SOURCE CODE
        </text>
        </a>
        <a href="${pageContext.request.contextPath}" class="navbar-brand d-flex align-items-center"><i
                class="fa fa-file-text fa-2x fa-fw lead d-inline-block" aria-hidden="true"></i>&nbsp;&nbsp;<text
                class="">DESCRIPTION
        </text>
        </a>
        <a href="/resume" class="navbar-brand d-flex align-items-center"><i
                class="fa fa-address-card fa-2x lead fa-fw d-inline-block" aria-hidden="true"></i>&nbsp;&nbsp;<text
                class="">RESUME
        </text>
        </a>
        <a href="/contact" class="navbar-brand d-flex align-items-center"><i
                class="fa fa-envelope fa-2x lead fa-fw d-inline-block" aria-hidden="true"></i>&nbsp;&nbsp;<text
                class="">CONTACT
        </text>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader"><span
                class="navbar-toggler-icon"></span></button>
    </div>
</div>
<div class="text-center py-4 bg-secondary"
     style="	background-image: linear-gradient(to left, rgba(0, 0, 0, 0.1), rgba(0, 0, 0, 0.9));	background-position: top left;	background-size: 100%;	background-repeat: repeat;">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-0">
                <h1 class="text-left text-primary">FTP CLIENT</h1>
                <p class="lead text-left">Lorem Ipsum jest tekstem stosowanym jako przykładowy wypełniacz w przemyśle
                    poligraficznym. Został po raz pierwszy użyty w XV w. przez nieznanego drukarza do wypełnienia
                    tekstem próbnej książki. Pięć wieków później zaczął być używany przemyśle elektronicznym, pozostając
                    praktycznie niezmienionym. </p>
            </div>
        </div>
    </div>
</div>


<nav class="navbar navbar-expand-md navbar-dark mb-4 bg-info" >
    <div class="container">
        <a class="navbar-brand" href="#">SERVER: ${serverName}</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarPrimaryContent">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse text-center justify-content-end" id="navbarPrimaryContent">
            <ul class="navbar-nav">
                <li class="nav-item mx-1">
                    <a class="nav-link active align-items-center d-flex" href="${pageContext.request.contextPath}/logout">
                        <i class="fa fa-sign-out fa-2x" aria-hidden="true"></i> &nbsp; LOGOUT</a>
                </li>
            </ul>
        </div>
    </div>
</nav>


<div class="pt-5 pb-0">
    <div class="container">
        <div class="row">
            <div class="col-md-2">
                <form action="${pageContext.request.contextPath}/new-directory">
                    <button class="btn w-100 btn-success" type="submit">New directory </button>
                </form>
            </div>
            <div class="col-md-2">
                <form action="${pageContext.request.contextPath}/new-file">
                    <button class="btn w-100 btn-success" type="submit">New file</button>
                </form>
            </div>
            <div class="col-md-2">
                <form action="${pageContext.request.contextPath}/send-file">
                    <button class="btn w-100 btn-info" type="submit">Send file</button>
                </form>
            </div>
            <div class="col-md-2">
                <form action="${pageContext.request.contextPath}/new-directory">
                    <button class="btn w-100 btn-danger" type="submit">Delete</button>
                </form>
            </div>
            <div class="col-md-2">
                <form action="${pageContext.request.contextPath}/new-directory">
                    <button class="btn w-100 btn-secondary" type="submit">Change name</button>
                </form>
            </div>
            <div class="col-md-2">
                <form action="${pageContext.request.contextPath}/new-directory">
                    <button class="btn w-100 btn-secondary" type="submit">Unzip</button>
                </form>
            </div>
        </div>
    </div>
</div>


<div class="py-5 border-0">
    <div class="container" style="min-height: 1000px">
        <div class="row">
            <h2 class="w-100 text-left">Web based FTP client</h2>
            <table class="table table-striped">
                <thead class="thead-light">
                <tr class="text-center">
                    <th>Select</th>
                    <th>Name</th>
                    <th>Action</th>
                    <th>Type</th>
                    <th>Size</th>

                </tr>
                </thead>
                <tbody class="text-center">
                <tr>
                    <td></td>
                    <td></td>
                    <td>
                        <form action="${pageContext.request.contextPath}/back" method="get">
                            <button type="submit" class="btn btn-dark w-50">
                                <i class="fa fa-arrow-left" aria-hidden="true"></i>&nbsp;Back
                            </button>
                        </form>
                    </td>
                    <td></td>
                    <td></td>
                </tr>
                <c:forEach var="file" items="${files}">
                    <tr>
                        <td><input type="checkbox" value="on" checked=""></td>
                        <td>${file.name}</td>
                        <td>
                            <c:choose>
                                <c:when test="${file.type=='1'}">
                                    <form action="${pageContext.request.contextPath}/directory" method="post">
                                        <button name="name" value="${file.name}" type="submit" formmethod="post"
                                                class="btn btn-dark w-50">
                                            <i class="fa fa-sign-in" aria-hidden="true"></i>&nbsp;Open
                                        </button>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <form action="${pageContext.request.contextPath}/file" method="post">
                                        <button name="name" value="${file.name}" type="submit"
                                                formmethod="post" class="btn btn-dark w-50">
                                            <i class="fa fa-download" aria-hidden="true"></i>&nbsp;Download
                                        </button>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${file.type}</td>
                        <td>${file.size}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<footer class="py-5 bg-dark text-muted">
    <div class="container">
        <p class="float-right">
            <a href="#">Back to top</a>
        </p>
        <p>© Copyright 2018 coffecode.org - All rights reserved.<br>Contact: info@coffecode.org<br>02-619 Warsaw<br><a
                href="https://www.coffecode.org/">Visit the homepage</a>
        </p>
    </div>
</footer>
</body>
</html>