<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <div class="header-left clearfix">
    <a href="javascript:void(0)" class="rmenu-icon"><img src="images/responsive/rmenu-icon.png" alt="rmenu"></a>
    <a href ng-click="showTrends()" class="logo"><img src="images/logo.png" class="logo-img" alt="logo"><img src="images/responsive/rlogo.png" class="res-logo" alt="logo"></a>
    <div class="top-nav">
      <ul>
        <li>
          <a  href ng-click="showTrends()">
          <i class="trends-icon"></i>
          <span><fmt:message key="header.trends"/> </span>
          </a>
        </li>
        <li>
          <a href ng-click="showMicroSegment()">
          <i class="segment-icon"></i>
          <span><fmt:message key="header.microsegment"/></span>
          </a>
        </li>
        <li>
          <a href ng-click="showPredict()">
          <i class="predict-icon"></i>
          <span><fmt:message key="header.predict"/></span>
          </a>
        </li>
        <li>
        	<div id="error" class="header-eror clearfix"> {{error}} </div>
        </li>
      </ul>
    </div>
  </div>
  
  <div class="header-right clearfix">
    <div class="header-notification">
      <ul>
        <li class="dropdown">
          <a href="" class="dropdown-toggle" data-toggle="dropdown">
          <img src="images/notification.png" alt="notification">
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
          <img src="images/msg.png" alt="notification">
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
      <a href="javascript:void(0)" id="dLabel" role="button" data-toggle="dropdown" data-target="#">
      <img src="images/user.png" class="user-img" alt="user-img"> Tim Brown <span class="caret"></span>
      </a>
      <ul class="dropdown-menu pull-right" role="menu" aria-labelledby="dLabel">
        <li><a href="">Profile</a>
        </li>
        <li><a href="">Logout</a>
        </li>
      </ul>
    </div>
  </div>
