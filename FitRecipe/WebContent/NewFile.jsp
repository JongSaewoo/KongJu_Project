<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- �� 3���� ��Ÿ �±״� *�ݵ��* head �±��� ó���� �;��մϴ�; � �ٸ� ���������� �ݵ�� �� �±׵� *������* �;� �մϴ� -->
    <title>����  ���� ȭ��</title>
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
					<li >���� �׽�Ʈ</li>
					<li>FQA</li>
					<li>ȸ������</li>
					<li><a href="login.jsp">�α���</li>									
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
            <li class="dropdown"> <a href="#" class="" data-toggle="dropdown" role="button" aria-expanded="false">������ ����</a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="#">���� Ư����</a></li>
                <li><a href="#">��⵵</a></li>
                <li><a href="#">��û����</a></li>
                <li><a href="#">��û�ϵ�</a></li>
                <li><a href="#">���ϵ�</a></li>
                <li><a href="#">��󳲵�</a></li>
                <li><a href="#">������</a></li>
                <li><a href="#">���󳲵�</a></li>
                <li><a href="#">����ϵ�</a></li>
                
              </ul>
                </li><li class="dropdown"> <a href="#" class="" data-toggle="dropdown" role="button" aria-expanded="false">�оߺ� ����</a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="pages/archive-mainfood.html">BestReview</a></li>
                    <li><a href="pages/archiveFood1.html">�������</a></li>
                    <li><a href="pages/archiveFood2.html">����������</a></li>
                    <li><a href="pages/archiveFood3.html">����ڸ���</a></li>
             </ul>
                </li><li class="dropdown"> <a href="#" class="" data-toggle="dropdown" role="button" aria-expanded="false">MakeUp</a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="pages/archive-mainMakeUp.html">BestReview</a></li>
                    <li><a href="pages/archiveMakeUp1.html">�������</a></li>
                    <li><a href="pages/archiveMakeUp2.html">����������</a></li>
                    <li><a href="pages/archiveMakeUp3.html">����ڸ���</a></li>
             </ul>
                </li><li class="dropdown"> <a href="#" class="" data-toggle="dropdown" role="button" aria-expanded="false">Car</a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="pages/archive-mainCar.html">BestReview</a></li>
                    <li><a href="pages/archiveCar1.html">�������</a></li>
                    <li><a href="pages/archiveCar2.html">����������</a></li>
                    <li><a href="pages/archiveCar3.html">����ڸ���</a></li>
              </ul>
                </li><li class="dropdown"> <a href="#" class="" data-toggle="dropdown" role="button" aria-expanded="false">Travel</a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="pages/archive-mainTravel.html">BestReview</a></li>
                    <li><a href="pages/archiveTravel1.html">�������</a></li>
                    <li><a href="pages/archiveTravel2.html">����������</a></li>
                    <li><a href="pages/archiveTravel3.html">����ڸ���</a></li>
              </ul>

              </li><li class="dropdown"> <a href="#" class="" data-toggle="dropdown" role="button" aria-expanded="false">Book</a>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="pages/archive-mainbook.html">BestReview</a></li>
                  <li><a href="pages/archivebook1.html">�������</a></li>
                  <li><a href="pages/archivebook2.html">����������</a></li>
                  <li><a href="pages/archivebook3.html">����ڸ���</a></li>
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
                   <img src="images/�Ľ�Ÿ.jpg" alt="First slide">
                </div>
                <div class="item">
                   <img src="images/������.jpg" alt="Second slide">               
                </div>
                <div class="item">
                   <img src="images/��ġ�.jpg" alt="Third slide" >                 
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
      <h1><i class="fa fa-thumbs-o-up"></i> �̴��� ��õ ����</h1>
    </header>
    <div class="row">
      <div class="col-md-3 col-sm-3"> 
        <div class="thumbnail">
           <a href="#">
                <img src="image1.jpg" alt="" >
                <div class="caption">          
                  <ul class="list-unstyled block">
                    <li><b>Ʈ����, ���ɽ�Ʈ�� �ܿ�</b></li>         
                    
                  </ul>
                  <dl>
                    <dt>���� </dt>
                    <dd>��û���� õ�Ƚ�</dd>
                    <dt class="red">���ð�</dt>
                    <dd class="red">759,000 ��</dd>
                  </dl>
               </div> 
   <!--           <div class="zoom"> �ڼ��� ���� </div> -->
            </a>    
         </div>  
        </div>
        <div class="col-md-3 col-sm-3">        
          <div class="thumbnail">
               <a href="#">
                <img src="image2.jpg" alt="" >
             
                <div class="caption">          
                  <ul class="list-unstyled block">
                    <li><b>Ʈ���� ����</b></li>         
                    
                  </ul>
                  <dl>
                    <dt>���� </dt>
                    <dd>����Ư���� ��������</dd>
                    <dt class="red">���ð�</dt>
                    <dd class="red">759,000 ��</dd>
                  </dl>
               </div> 
   <!--           <div class="zoom"> �ڼ��� ���� </div> -->
            </a>    
           </div>   
        </div>            
        <div class="col-md-3 col-sm-3">
          <div class="thumbnail">
              <a href="#">
                <img src="image3.jpg" alt="" >            
                <div class="caption">          
                  <ul class="list-unstyled block">
                    <li><b>���� 1:1 ����</b></li>         
                    
                  </ul>
                  <dl>
                    <dt>���� </dt>
                    <dd>848,000 ��</dd>
                    <dt class="red">���ð�</dt>
                    <dd class="red">759,000 ��</dd>
                  </dl>
               </div> 
   <!--           <div class="zoom"> �ڼ��� ���� </div> -->
            </a>    
           </div>             
        </div>
        <div class="col-md-3 col-sm-3">
         <div class="thumbnail">
            <a href="#">
                <img src="image4.jpg" alt="" >
                <div class="caption">          
                  <ul class="list-unstyled block">
                    <li><b>���� Ʈ���̴� </b></li>         
                    
                  </ul>
                  <dl>
                    <dt>���� </dt>
                    <dd>848,000 ��</dd>
                    <dt class="red">���ð�</dt>
                    <dd class="red">759,000 ��</dd>
                  </dl>
               </div> 
   <!--           <div class="zoom"> �ڼ��� ���� </div> -->
            </a>    
           </div>             
        </div>
      </div>
</div>
  
  
  
        
  </div> 
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
 
 <footer>
 
<div id="footer_d">
  ������ �̽¿�     //   2017  121 21 2 1212 
  </div>   

 
 </footer>
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 

    <!-- jQuery (��Ʈ��Ʈ���� �ڹٽ�ũ��Ʈ �÷������� ���� �ʿ��մϴ�) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- ��� �����ϵ� �÷������� �����մϴ� (�Ʒ�), ������ �ʴ´ٸ� �ʿ��� ������ ������ �����ϼ��� -->
    <script src="js/bootstrap.min.js"></script>
	 <script>
      $('.carousel').carousel()
    </script>
	
  </body>
</html>