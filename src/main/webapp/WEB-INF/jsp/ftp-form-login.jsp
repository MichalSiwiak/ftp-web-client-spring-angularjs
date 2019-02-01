<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>coffeecoding.net</title>
    <meta name="description"
          content="Free open source projects present different java solutions using spring, hibernate and other popular frameworks.">
    <meta name="keywords"
          content="java, spring, hibernate, apache, tomcat, coding, programmer, linux, google cloud platform, open source, bootstrap, mysql, java ideas">
    <!-- CSS dependencies -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
          type="text/css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.standalone.min.css">
    <link rel="stylesheet" href="https://www.coffeecoding.net/resources/css/now-ui-kit.css" type="text/css">
    <link rel="stylesheet" href="https://www.coffeecoding.net/resources/css/style.css" type="text/css">
    <link rel="icon" href="resources/img/favicon.png">
    <!-- PAGE scripts -->
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


<body class="bg-light text-dark">
<div id="wrap">
    <div id="main" class="clear-top">


        <div class="collapse" id="navbarHeader">
            <div class="container">
                <div class="row">
                    <div class="col-md-7 py-4">
                        <h4>About</h4>
                        <p class="text-info">Free open source projects present different java solutions using spring,
                            hibernate
                            and other popular frameworks.</p>
                    </div>
                    <div class="col-md-3 offset-md-1 py-4">
                        <h4>Contact</h4>
                        <ul class="list-unstyled">
                            <li>
                                <a href="https://pl.linkedin.com/in/michalsiwiak" class="text-secondary"
                                   target="_blank">Follow
                                    on LinkedIn</a>
                            </li>
                            <li>
                                <a href="mailto:info@coffeecoding.net" target="_top" class="text-secondary">Email me</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>


        <div class="navbar sticky-top navbar-dark bg-info">
            <div class="container d-flex justify-content-between">
                <a href="https://www.coffeecoding.net/" class="navbar-brand d-flex align-items-center"><i
                        class="fa fa-home fa-2x lead fa-fw d-inline-block" aria-hidden="true"></i>&nbsp;&nbsp;<text
                        class="">
                    HOME
                </text>
                </a>
                <a href="https://github.com/MichalSiwiak/ftp-web-client-spring-angularjs" target="_blank"
                   class="navbar-brand d-flex align-items-center"><i
                        class="fa fa-git-square fa-fw d-inline-block lead fa-2x"></i>&nbsp;&nbsp;<text class="">SOURCE
                    CODE
                </text>
                </a>
                <a href="${pageContext.request.contextPath}" class="navbar-brand d-flex align-items-center"><i
                        class="fa fa-file-text fa-2x fa-fw lead d-inline-block" aria-hidden="true"></i>&nbsp;&nbsp;<text
                        class="">DESCRIPTION
                </text>
                </a>
                <a href="https://coffeecoding.net/resources/img/cv_msiwiak.pdf" target="_blank"
                   class="navbar-brand d-flex align-items-center"><i
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
                        <p class="lead text-left">Implementation of basic FTP client functionality using spring mvc and
                            apache commons net.</p>
                    </div>
                </div>
            </div>
        </div>


        <div class="container">
            <div class="row my-4">
                <div class="mx-auto col-md-6 col-10 col-xl-4 px-4">
                    <div class="card bg-primary rounded">
                        <div class="card-body text-center rounded bg-dark">
                            <div class="row mt-5">
                                <div class="col-md-12">
                                    <h5 class="mb-4"><b>FTP WEB CLIENT</b></h5>
                                </div>
                                <c:if test="${error != null}">
                                    <div class="alert alert-danger col-xs-offset-1 col-xs-10 w-100 text-light"> ${error} </div>
                                </c:if>
                                <c:if test="${logout != null}">
                                    <div class="alert alert-info col-xs-offset-1 col-xs-10 w-100 text-light"> ${logout} </div>
                                </c:if>
                            </div>
                            <div class="row pt-2 mt-0">
                                <div class="col bg-dark">
                                    <form action="${pageContext.request.contextPath}/authenticate" method="POST"
                                          autocomplete="off">
                                        <div class="form-group mb-2">
                                            <div class="input-group border-0">
                                                <div class="input-group-prepend">
                          <span class="input-group-text">
                        <i class="fa fa-server text-light" aria-hidden="true"></i>
                          </span>
                                                </div>
                                                <input name="serverName" type="text"
                                                       class="form-control text-center"
                                                       placeholder="server name"/>
                                            </div>
                                        </div>
                                        <div class="form-group mb-2">
                                            <div class="input-group border-0">
                                                <div class="input-group-prepend">
                          <span class="input-group-text">
                        <i class="fa fa-plug text-light" aria-hidden="true"></i>
                          </span>
                                                </div>
                                                <input name="portNumber" type="number" value="21"
                                                       class="form-control text-center"
                                                       placeholder="port number"/>
                                            </div>
                                        </div>
                                        <div class="form-group mb-2">
                                            <div class="input-group border-0">
                                                <div class="input-group-prepend">
                          <span class="input-group-text">
                           <i class="fa fa-user fa-lg text-light" aria-hidden="true"></i>
                          </span>
                                                </div>
                                                <input name="username" type="text"
                                                       class="form-control text-center"
                                                       placeholder="username"/>
                                            </div>
                                        </div>
                                        <div class="form-group mb-3">
                                            <div class="input-group border-0">
                                                <div class="input-group-prepend ">
                          <span class="input-group-text">
                            <i class="fa fa-lock fa-lg text-light" aria-hidden="true"></i>
                          </span>
                                                </div>
                                                <input name="password" type="password"
                                                       class="form-control text-center"
                                                       placeholder="password"/>
                                            </div>
                                        </div>
                                        <button type="submit"
                                                class="btn mt-4 mb-3 rounded btn-lg btn-primary text-light w-100">Login
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


    </div>
</div>


<footer class="footer bg-dark text-muted">
    <div class="container">
        <p class="float-right">
            <a href="#">Back to top</a>
        </p>
        <p>© Copyright 2018 coffeecoding.net - All rights reserved.<br>Contact: info@coffeecoding.net<br>Warsaw PL<br><a
                href="https://www.coffeecoding.net/">Visit the homepage</a>
        </p>
    </div>
</footer>


</body>
</html>