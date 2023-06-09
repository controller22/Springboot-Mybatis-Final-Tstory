<!DOCTYPE html><%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%><%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html lang="en">
    <head>
        <title>제이스토리</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
        />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
        <script
            src="https://kit.fontawesome.com/6b3377d2bb.js"
            crossorigin="anonymous"
        ></script>
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
            rel="stylesheet"
        />

        <!-- drawer.css -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/drawer/3.2.2/css/drawer.min.css"
        />
        <!-- jquery & iScroll -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/iScroll/5.2.0/iscroll.min.js"></script>
        <!-- drawer.js -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/drawer/3.2.2/js/drawer.min.js"></script>
        <link
            href="https://cdn.quilljs.com/1.3.6/quill.snow.css"
            rel="stylesheet"
        />

        <!--writeForm-->
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" rel="stylesheet">


        <link rel="stylesheet" href="/css/styles.css" />
    </head>

    <body>
        <div style="margin-bottom: 50px">
            <input
                type="hidden"
                id="principal-id"
                value="${principal.userId}"
            />

            <nav class="navbar navbar-expand-sm my_navbar">
                <!-- T모양 -->
                <a href="/">
                    <svg
                        class="my_sm_1"
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 408.4 408.4"
                    >
                        <g>
                            <circle
                                class="cls-1"
                                cx="58.18"
                                cy="58.18"
                                r="58.18"
                            />
                            <circle
                                class="cls-1"
                                cx="204.2"
                                cy="58.18"
                                r="58.18"
                            />
                            <circle
                                class="cls-1"
                                cx="204.2"
                                cy="204.2"
                                r="58.18"
                            />
                            <circle
                                class="cls-1"
                                cx="204.2"
                                cy="350.22"
                                r="58.18"
                            />
                            <circle
                                class="cls-1"
                                cx="350.22"
                                cy="58.18"
                                r="58.18"
                            />
                        </g>
                    </svg>
                </a>
                <div class="my_navbar_title">
                    <a class="drawer-brand" href="/">제이스토리 </a>
                </div>
        <c:choose>
    <c:when  test="${principal != null}">
        <c:choose>
              <c:when  test="${userImg.profileImg==null}">   <!-- 페이지 이동 -->
                        <div class="dropdown dropleft">
                            <div data-toggle="dropdown">
                                    <img id="profileImg"  src="/img/기본프로필사진.jpg" style="width: 80px;height:80px;border-radius: 70%;"> <!-- 사진 사이즈 조절 -->
                                 
                            </div>

                            <div class="dropdown-menu">
                                <a
                                    class="dropdown-item"
                                    href="/api/subscribe/listForm"
                                    >구독관리</a
                                >
                                <a
                                    class="dropdown-item"
                                    href="/post/listForm/${principal.userId}"
                                    >내블로그</a
                                >
                                <a
                                    class="dropdown-item"
                                    href="/api/passwordCheckForm"
                                    >계정관리</a
                                >
                                <a
                                    class="dropdown-item"
                                    href="/api/category/writeForm"
                                    >카테고리등록</a
                                >

                                <c:choose>
                                <c:when  test="${principal.role=='admin'}">
                                 <a
                                    class="dropdown-item"
                                    href="/admin/memberManageForm"
                                    >관리자</a
                                >
                                 </c:when>
                                 </c:choose>

                                <a class="dropdown-item" href="/logout"
                                    >로그아웃</a
                                >
                            </div>
                        </div> </c:when>
              <c:otherwise>  <!-- 페이지 이동 -->
                        <div class="dropdown dropleft">
                            <div data-toggle="dropdown">
                                 <img id="profileImg"  src="/img/${userImg.profileImg}" style="width: 80px;height:80px;border-radius: 70%;"> <!-- 사진 사이즈 조절 -->
                            </div>

                            <div class="dropdown-menu">
                                <a
                                    class="dropdown-item"
                                    href="/s/api/subscribe/listForm"
                                    >구독관리</a
                                >
                                <a
                                    class="dropdown-item"
                                    href="/post/listForm/${principal.userId}"
                                    >내블로그</a
                                >
                                <a
                                    class="dropdown-item"
                                    href="/api/passwordCheckForm"
                                    >계정관리</a
                                >
                                <a
                                    class="dropdown-item"
                                    href="/api/category/writeForm"
                                    >카테고리등록</a
                                >
                                 <c:choose>
                                <c:when  test="${principal.role=='admin'}">
                                 <a
                                    class="dropdown-item"
                                    href="/admin/memberManageForm"
                                    >관리자</a
                                >
                                 </c:when>
                                 </c:choose>

                                <a class="dropdown-item" href="/logout"
                                    >로그아웃</a
                                >
                            </div>
                        </div> 
                    </c:otherwise>
                </c:choose>
    </c:when>
    <c:otherwise>
           <div>
                <a class="my_main_start_btn" href="/loginForm">시작하기</a>
            </div>
    </c:otherwise>
</c:choose> 


            </nav>
            <br />
        </div>