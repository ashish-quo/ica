<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<header class="clearfix">
  <div class="header-left clearfix">
    <a href="javascript:void(0)" class="rmenu-icon"><img src="images/responsive/rmenu-icon.png" alt="rmenu"></a>
    <a href ng-click="showHome()" class="logo"><img src="images/logo.png" class="logo-img" alt="logo"><img src="images/responsive/rlogo.png" class="res-logo" alt="logo">{{roamType}}</a>
    <div class="top-nav">
      <ul>
        <li>
          <a ng-class="{'active': tabIndex == 0}"  href ng-click='showHome()'>
          <i class="home-icon"></i>
          <span>Home</span>
          </a>
        </li>
        <li>
          <a ng-class="{'active': tabIndex == 1}" href ng-click='showTrends()'>
          <i class="trends-icon"></i>
          <span><fmt:message key="header.trends"/> </span>
          </a>
        </li>
        <li>
          <a  ng-class="{'active': tabIndex == 2}" href ng-click='showMicroSegment()'>
          <i class="segment-icon"></i>
          <span><fmt:message key="header.microsegment"/></span>
          </a>
        </li>
        <li style="display:none">
          <a ng-class="{'active': tabIndex == 3}" href ng-click='showBeforeTravel()'>
          <i class="pretrip-icon"></i>
          <span>Before Travel</span>
          </a>
        </li>
        <li style="display:none">
          <a ng-class="{'active': tabIndex == 4}" href ng-click='showUponLanding()'>
          <i class="posttrip-icon"></i>
          <span>Upon Landing</span>
          </a>
        </li>
        <li>
        	 <div id="error" class="header-eror clearfix" style="display:none"> {{error}} </div><!-- added by cheshta for angular {{}} -->
        </li>
      </ul>
    </div>
  </div>
  <div class="header-right clearfix">
    <div class="header-notification" style="display:none">
      <ul>
        <li class="dropdown">
          <a href="" class="dropdown-toggle" data-toggle="dropdown">
          <i class="notification-icon"></i><!--<img src="images/notification.png" alt="notification">-->
          <span class="noti-num">33</span>
          </a>
          <ul class="dropdown-menu notify-dropdown popover bottom">
            <div class="arrow"></div>
            <li><a href="javascript:void(0)">Notification 1</a>
            </li>
            <li><a href="javascript:void(0)">Notification 2</a>
            </li>
            <li><a href="javascript:void(0)">Notification 3</a>
            </li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="" class="dropdown-toggle" data-toggle="dropdown">
          <i class="msg-icon"></i><!--<img src="images/msg.png" alt="notification">-->
          <span class="noti-num msg-noti">1</span>
          </a>
          <ul class="dropdown-menu notify-dropdown popover bottom">
            <div class="arrow"></div>
            <li><a href="javascript:void(0)">Comment 1</a>
            </li>
            <li><a href="javascript:void(0)">Comment 2</a>
            </li>
            <li><a href="javascript:void(0)">Comment 3</a>
            </li>
          </ul>
        </li>
      </ul>
    </div>
    
    <div class="dropdown user-dropdown">
    <ul>
        <li class="dropdown">
          <a href="" class="dropdown-toggle" data-toggle="dropdown">
          <img src="images/user.png" class="user-img" alt="user-img"> Tim Brown <span class="caret"></span>
          </a>
          <ul class="dropdown-menu notify-dropdown popover bottom">
            <div class="arrow"></div>
            <li style="display:none"><a href="">Profile</a></li>
       		<li><a href="./perform_logout">Logout</a></li>
          </ul>
        </li>
        
      </ul>
      </div>
  </div>
</header>




<!-- Don't Remove this -->
<div class="loaderoverlay">
	</p>
</div>
<a id="totop" href="javascript:void(0)"><i class="fa fa-angle-up"></i></a>
