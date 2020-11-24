<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>오프  메인 화면</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">

    
	
	<style>
	 
     body {  font-family: "Malgun gothic",  sans-serif; padding-top: 70px;}
     .control { position: inherit; top: 50%; z-index: 5; display: inline-block; right: 50%;} 
     header { padding: 10px 0}
    
	    #header_nav {
            width:100%;
            overflow:hidden;
		border-bottom: 1px solid #e6e6e6;        
        }
		#header_nav li{
            font-size:0.9em;    
            margin-right:20px;
            list-style:none;  
            float:right;
        }
		

	 
 
 
 
 	#nav {
        float: left;
        display: inline;
       width: 100%;
         }
		 
		 #navd{
		 border-radius: 0;
		 padding: 0;
		 margin-bottom: 0px;
		 }
		 
		  #navd li{
		  font-size:0.9em;
		  list-style:none; 
		 }
	
	.navbar-default {
    border-radius: 10;
    padding: 0;
    margin-bottom: 0px;
}

  .navbar-default {
    background-color: #F0C823;
   
    }
	.navbar-default .navbar-toggle .icon-bar {
    background-color: #fff;
}

 .white {
 
 background-color: #ccc;
    border-color: #ccc;
	}
.navbar-default .navbar-nav > li > a {
    color: #fff;
    display: block;
    font-family: "Oswald",sans-serif;
    font-size: 15px;
    text-decoration: none;
    text-transform: uppercase;
    border-top: 2px solid transparent;
    border-bottom: 2px solid transparent;
    margin-top: -2px;
    margin-bottom: -2px;
    padding-bottom: 17px;
    -webkit-transition: all 0.5s;
    -o-transition: all 0.5s;
    transition: all 0.5s;
}
.custom_nav {
  
    width: 100%;
}

.navbar-default .navbar-collapse, .navbar-default .navbar-form {
    border-color: #e7e7e7;
}
  
	
	.navbar-default .navbar-nav > li > a {
    color: #fff;
    display: block;
    font-family: "Oswald",sans-serif;
    font-size: 15px;
    text-decoration: none;
    text-transform: uppercase;
    border-top: 2px solid transparent;
    border-bottom: 2px solid transparent;
    margin-top: -2px;
    margin-bottom: -2px;
    padding-bottom: 17px;
    -webkit-transition: all 0.5s;
    -o-transition: all 0.5s;
    transition: all 0.5s;
}	

		
		.search{
		float :right;
		
		}
		
		
	.btn_search {
	       
            width:124px; height:38px;
            background:url(./search_btn.png) no-repeat 0 0;
            border:0;
		
        }	
		.search input {
		   
            width:200px;
            font-size:20px;
            border:1px solid #3488d9;
            margin-top:6px;
            padding:5px;
			border-radius : 5px;
			
        }
		
		.txt_sw {
    color: #ffffff;
    font-size: 1.2em;
        }
		
	
	
	
	.carousel-inner {
    position: relative;
    width: 100%;
    overflow: hidden;
	margin-top : 50px;
	margin-bottom : 50px;
}
	
	
	

	
	
	
	
	
	 .thumbnail { border-radius: 10px; position: relative;
              	 z-index: 1; border: 2px solid #fff; outline: 1px solid #ccc;
				 }     
    .thumbnail:hover { border: 2px solid #F0C823; outline: 0; }
    .thumbnail a:hover { text-decoration: none}
    .thumbnail:hover img{   position: relative; top: -4px;   
	                          }
	
	#footer_d{
       padding: 20px;
	  width:1000px;
            margin:0 auto;
	   border-top: 1px solid #e6e6e6;        
         }
	
	
	
	
	</style>
  </head>
  <body>
  <div class="container">
   <header id="header">
     
    
	
	<div id="header_nav">
			<div>
				<ul>
					<li >에러 테스트</li>
					<li>FQA</li>
					<li>회원가입</li>
					<li><a href="login.jsp">로그인</li>									
				</ul>
			</div> 
		</div>		
	
	
	

   
   
   
   </header>

<div id="image"> 

 <div class="col-lg-2 col-md-2">
 <img src="of_main.png">
 </div>
 
  <div class="col-lg-2 col-md-2">
	<img src="main_info.png" id="main_info">
 </div>
 
 
 <div class="col-lg-8 col-md-8">
 </div>
 </div>


	
	
	<div id="nav">
    <div id="navarea">
    <nav class="navbar navbar-default" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
             <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav custom_nav">
            <li class="white"><a href="off.html">Home</a></li>
            <li class="dropdown"> <a href="#" class="" data-toggle="dropdown" role="button" aria-expanded="false">지역별 강의</a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="#">서울 특별시</a></li>
                <li><a href="#">경기도</a></li>
                <li><a href="#">충청남도</a></li>
                <li><a href="#">충청북도</a></li>
                <li><a href="#">경상북도</a></li>
                <li><a href="#">경상남도</a></li>
                <li><a href="#">강원도</a></li>
                <li><a href="#">전라남도</a></li>
                <li><a href="#">전라북도</a></li>
                
              </ul>
                </li><li class="dropdown"> <a href="#" class="" data-toggle="dropdown" role="button" aria-expanded="false">분야별 강의</a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="pages/archive-mainfood.html">BestReview</a></li>
                    <li><a href="pages/archiveFood1.html">기업리뷰</a></li>
                    <li><a href="pages/archiveFood2.html">전문가리뷰</a></li>
                    <li><a href="pages/archiveFood3.html">사용자리뷰</a></li>
             </ul>
                </li><li class="dropdown"> <a href="#" class="" data-toggle="dropdown" role="button" aria-expanded="false">MakeUp</a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="pages/archive-mainMakeUp.html">BestReview</a></li>
                    <li><a href="pages/archiveMakeUp1.html">기업리뷰</a></li>
                    <li><a href="pages/archiveMakeUp2.html">전문가리뷰</a></li>
                    <li><a href="pages/archiveMakeUp3.html">사용자리뷰</a></li>
             </ul>
                </li><li class="dropdown"> <a href="#" class="" data-toggle="dropdown" role="button" aria-expanded="false">Car</a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="pages/archive-mainCar.html">BestReview</a></li>
                    <li><a href="pages/archiveCar1.html">기업리뷰</a></li>
                    <li><a href="pages/archiveCar2.html">전문가리뷰</a></li>
                    <li><a href="pages/archiveCar3.html">사용자리뷰</a></li>
              </ul>
                </li><li class="dropdown"> <a href="#" class="" data-toggle="dropdown" role="button" aria-expanded="false">Travel</a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="pages/archive-mainTravel.html">BestReview</a></li>
                    <li><a href="pages/archiveTravel1.html">기업리뷰</a></li>
                    <li><a href="pages/archiveTravel2.html">전문가리뷰</a></li>
                    <li><a href="pages/archiveTravel3.html">사용자리뷰</a></li>
              </ul>

              </li><li class="dropdown"> <a href="#" class="" data-toggle="dropdown" role="button" aria-expanded="false">Book</a>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="pages/archive-mainbook.html">BestReview</a></li>
                  <li><a href="pages/archivebook1.html">기업리뷰</a></li>
                  <li><a href="pages/archivebook2.html">전문가리뷰</a></li>
                  <li><a href="pages/archivebook3.html">사용자리뷰</a></li>
            </ul>
          
            </li>
           
		   
		   
		    <div class="search">
                <input type="text" />
                <button type="submit" class="btn_search"><span class="txt_sw">search web</span></button>
            </div>
		   
		   
          </ul>
        </div>
      </div>
    </nav>
	</div>
	</div>
	
	
	
	
	

	
	
	
	
	<div class="col-lg-6 col-md-6">
	
	<div id="carousel-example-generic" class="carousel slide">
            <!-- Indicators -->
            <ol class="carousel-indicators">
              <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
              <li data-target="#carousel-example-generic" data-slide-to="1"></li>
              <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            </ol>
                 <!-- Carousel items -->
             <div class="carousel-inner">
                <div class="item active">
                   <img src="images/파스타.jpg" alt="First slide">
                </div>
                <div class="item">
                   <img src="images/볶음밥.jpg" alt="Second slide">               
                </div>
                <div class="item">
                   <img src="images/김치찌개.jpg" alt="Third slide" >                 
                </div>
             </div>
            <!-- Controls -->
              <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                <span class="icon-prev"></span>
              </a>
              <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                <span class="icon-next"></span>
              </a>
          </div>
 </div>
 <div class="col-lg-3 col-md-3"> 
	
 </div>
 
  <div class="col-lg-3 col-md-3">
 </div>
 
 </div>
 
  
  
  <div class="container">

    <header>
      <h1><i class="fa fa-thumbs-o-up"></i> 이달의 추천 강의</h1>
    </header>
    <div class="row">
      <div class="col-md-3 col-sm-3"> 
        <div class="thumbnail">
           <a href="#">
                <img src="image1.jpg" alt="" >
                <div class="caption">          
                  <ul class="list-unstyled block">
                    <li><b>트럼펫, 오케스트라 단원</b></li>         
                    
                  </ul>
                  <dl>
                    <dt>지역 </dt>
                    <dd>충청남도 천안시</dd>
                    <dt class="red">혜택가</dt>
                    <dd class="red">759,000 원</dd>
                  </dl>
               </div> 
   <!--           <div class="zoom"> 자세히 보기 </div> -->
            </a>    
         </div>  
        </div>
        <div class="col-md-3 col-sm-3">        
          <div class="thumbnail">
               <a href="#">
                <img src="image2.jpg" alt="" >
             
                <div class="caption">          
                  <ul class="list-unstyled block">
                    <li><b>트럼펫 강의</b></li>         
                    
                  </ul>
                  <dl>
                    <dt>지역 </dt>
                    <dd>서울특별시 영등포구</dd>
                    <dt class="red">혜택가</dt>
                    <dd class="red">759,000 원</dd>
                  </dl>
               </div> 
   <!--           <div class="zoom"> 자세히 보기 </div> -->
            </a>    
           </div>   
        </div>            
        <div class="col-md-3 col-sm-3">
          <div class="thumbnail">
              <a href="#">
                <img src="image3.jpg" alt="" >            
                <div class="caption">          
                  <ul class="list-unstyled block">
                    <li><b>마술 1:1 교습</b></li>         
                    
                  </ul>
                  <dl>
                    <dt>지역 </dt>
                    <dd>848,000 원</dd>
                    <dt class="red">혜택가</dt>
                    <dd class="red">759,000 원</dd>
                  </dl>
               </div> 
   <!--           <div class="zoom"> 자세히 보기 </div> -->
            </a>    
           </div>             
        </div>
        <div class="col-md-3 col-sm-3">
         <div class="thumbnail">
            <a href="#">
                <img src="image4.jpg" alt="" >
                <div class="caption">          
                  <ul class="list-unstyled block">
                    <li><b>보컬 트레이닝 </b></li>         
                    
                  </ul>
                  <dl>
                    <dt>지역 </dt>
                    <dd>848,000 원</dd>
                    <dt class="red">혜택가</dt>
                    <dd class="red">759,000 원</dd>
                  </dl>
               </div> 
   <!--           <div class="zoom"> 자세히 보기 </div> -->
            </a>    
           </div>             
        </div>
      </div>
</div>
  
  
  
        
  </div> 
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
 
 <footer>
 
<div id="footer_d">
  제작자 이승원     //   2017  121 21 2 1212 
  </div>   

 
 </footer>
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 

    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="js/bootstrap.min.js"></script>
	 <script>
      $('.carousel').carousel()
    </script>
	
  </body>
</html>