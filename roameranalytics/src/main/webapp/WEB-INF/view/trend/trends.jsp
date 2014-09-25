<div class="row dashboard-top clearfix">
	<div class="main-heading-ar pull-left">
        <h1 class="maincontent-heading">TRENDS</h1>
      </div>
	<div class="col-lg-7">
		<div class="tag-div" ng-repeat="filter in filters.countries">
			{{filter.name}} <a href ng-click='removeCounryFilter(filter.name,true)' class="delete-tag"></a>
		</div>
		<div class="tag-div" ng-repeat="filter in filters.personas">
			{{filter.name}} <a href ng-click='removePersonaFilter(filter.id,true)' class="delete-tag"></a>
		</div>
		<span ng-repeat="(key, value) in filters.attributes">
			<div class="tag-div" ng-repeat="filter in value">
			{{filter.name}} <a href ng-click='removeAttributeFilter(key,filter.attrId, filter.catId,true)' class="delete-tag"></a>
			</div>
		</span>
		
	</div>
	<div class="col-lg-2 pull-right">
        <div class="commentshare-icon">
          <span class="dropdown">
          <a href="javascript:void(0)" class="greycomment-icon cust-tooltip" data-toggle="dropdown" original-title="Comment"></a>
          <div class="dropdown-menu pull-right comment-dropdown popover bottom stay-dd">
            <div class="arrow"></div>
            <div class="commentdd-content">
              <p class="trackdd-title">Add Comment</p>
              <textarea class="track-textarea" placeholder="Add Comment"></textarea>
              <button type="button" class="btn btn-primary">Save</button>
              <button type="button" class="btn btn-primary unactive">Cancel</button>
            </div>
            <div class="commentdd-footer">Share With <a href="javascript:void(0)" class="share-public"></a>
              <a href="javascript:void(0)" class="share-frnds active"></a>
              <a href="javascript:void(0)" class="share-onlyme"></a>
            </div>
          </div>
          </span>
          <a href="javascript:void(0)" class="share-icon cust-tooltip" original-title="Share"></a>
        </div>
      </div>
</div>

<div class="top10chart-view clearfix" ng-controller="RoamingTrendController">
	 <div class="col-lg-12">
          <ul class="nav-tabs-chart" role="tablist">
            <li>
              <div class="btn-group arpv-btns" data-toggle="buttons">
                <label class="btn btn-primary active">
                  <input type="radio" name="options" id="allarpv" checked >
                 <a href="#linechart_fr" data-toggle="tab" ng-click="dow = 'true'"> Day Of Week</a> </label>
                <label class="btn btn-primary">
                  <input type="radio" name="options" id="higharpv" value='false'>
                 <a href="#linechart_bk" data-toggle="tab" ng-click="dow = 'false'"> Per Day</a></label>
                
              </div>
            </li>
           
          </ul>
       </div>
      <div class="tab-content">
      <div class="tab-pane active" id="linechart_fr">   
	<div class="top10chart-trends clearfix">
		<div class="col-lg-3">
			<div class="topChart-panel-tr">
				<div class="front-top">
					<div class="linechart-box-tr">
						<div class="topChart-angle pull-left extrlightblue clearfix">
							<input type="button" id="rom-opt-b" value="Log Scale" ng-model="logScale" ng-click="logScale='true'"
								ng-class="{'active-scale' : logScale == 'true'}"
								class="angle_btn "> <input type="button" id="rom-opt-a" ng-model="logScale" 
								ng-click="logScale='false'" ng-class="{'active-scale': logScale == 'false'}" 
								value="Linear Scale" class="angle_btn ">
						</div>
						 <div class="pull-right">
		                    <!-- Button trigger modal -->
		                    <div id="roamer-ft-btn" class="top10charts-zoom zoom-btn" ng-click="zoom(roamerCountChartConfig,true)">
		                    	 <img src="images/zoom-icon.png" >
		                    </div>
						</div>
						<highchart id="roamerCountChart" config="roamerCountChartConfig"
							class="container-chart-box"></highchart>
					</div>
				</div>
				<div class="top10chart-footer lightblue clearfix">
					<p class="chart-name">Roamers</p>
<!-- 					<div class="chart-radio lightblue"> -->
<!-- 						<p class="i-checks"> -->
<!-- 							<label> <input type="radio" checked -->
<!-- 								name="countChartOption" id="countChartOptionDoW" value="true" -->
<!-- 								ng-model="countDoW"> <i></i></label> <label -->
<!-- 								for="countChartOptionDoW">Day of week</label> -->
<!-- 						</p> -->
<!-- 						<p class="i-checks"> -->
<!-- 							<label> <input type="radio" name="countChartOption" -->
<!-- 								id="countChartOptionPD" value="false" ng-model="countDoW"> -->
<!-- 								<i></i></label> <label for="countChartOptionPD">Per day</label> -->
<!-- 						</p> -->
<!-- 					</div> -->
				</div>
			</div>
		</div>
		<div class="col-lg-3">
			<div class="topChart-panel-tr">
				<div class="front-top">
					<div class="linechart-box-tr">
						<div class="pull-right">
                    		<!-- Button trigger modal -->
                    		<div id="voice-ft-btn" class="top10charts-zoom zoom-btn" ng-click="zoom(roamerVoiceChartConfig,false)">
                    		 	<img src="images/zoom-icon.png" >
                   			 </div>
						</div>
						<highchart id="roamerVoiceChart" config="roamerVoiceChartConfig"
							class="container-chart-box"></highchart>
					</div>
				</div>
				<div class="top10chart-footer green clearfix">
					<p class="chart-name">Voice (Min)</p>
<!-- 					<div class="chart-radio green"> -->
<!-- 						<p class="i-checks"> -->
<!-- 							<label> <input type="radio" name="voiceChartOption" -->
<!-- 								id="voiceChartOptionDoW" ng-model="voiceDoW" value="true" -->
<!-- 								checked> <i></i></label> <label for="voiceChartOptionDoW">Day -->
<!-- 								of week</label> -->
<!-- 						</p> -->
<!-- 						<p class="i-checks"> -->
<!-- 							<label> <input type="radio" name="voiceChartOption" -->
<!-- 								id="voiceChartOptionPD" ng-model="voiceDoW" value="false"> -->
<!-- 								<i></i></label> <label for="voiceChartOptionPD">Per day</label> -->
<!-- 						</p> -->
<!-- 					</div> -->
				</div>
			</div>
		</div>
		<div class="col-lg-3">
			<div class="topChart-panel-tr">
				<div class="front-top">
					<div class="linechart-box-tr">
						 <div class="pull-right">
		                    <!-- Button trigger modal -->
		                     <div id="data-ft-btn" class="top10charts-zoom zoom-btn" ng-click="zoom(roamerDataChartConfig,false)">
		                     	<img src="images/zoom-icon.png" >
		                    </div>                    
						</div>
						<highchart id="roamerDataChart" config="roamerDataChartConfig"
							class="container-chart-box"></highchart>
					</div>
				</div>
				
				<div class="top10chart-footer dark-light-orange clearfix">
					<p class="chart-name">Data (MB)</p>
<!-- 					<div class="chart-radio dark-light-orange"> -->
<!-- 						<p class="i-checks"> -->
<!-- 							<label> <input type="radio" name="dataDataOption" -->
<!-- 								id="dataChartOptionDoW" ng-model="dataDoW" value="true" -->
<!-- 								checked> <i></i></label> <label for="dataChartOptionDoW">Day -->
<!-- 								of week</label> -->
<!-- 						</p> -->
<!-- 						<p class="i-checks"> -->
<!-- 							<label> <input type="radio" name="dataChartOption" -->
<!-- 								id="dataChartOptionPD" ng-model="dataDoW" value="false"> -->
<!-- 								<i></i></label> <label for="dataChartOptionPD">Per day</label> -->
<!-- 						</p> -->
<!-- 					</div> -->
				</div>
			</div>
		</div>
		<div class="col-lg-3">
			<div class="topChart-panel-tr">
				<div class="front-top">
					<div class="linechart-box-tr">
						<div class="pull-right">
		                    <!-- Button trigger modal -->
		                    <div id="sms-bk-btn" class="top10charts-zoom zoom-btn" ng-click="zoom(roamerSMSChartConfig,false)">
		                     	<img src="images/zoom-icon.png" >
		                    </div> 
						</div>
						<highchart id="roamerSMSChart" config="roamerSMSChartConfig"
							class="container-chart-box"></highchart>
					</div>
				</div>
				<div class="top10chart-footer yellow clearfix">
					<p class="chart-name">SMS</p>
<!-- 					<div class="chart-radio yellow"> -->
<!-- 						<p class="i-checks"> -->
<!-- 							<label> <input type="radio" name="smsChartOption" -->
<!-- 								id="smsChartOptionDoW" ng-model="smsDoW" value="true" -->
<!-- 								checked> <i></i></label> <label for="smsChartOptionDoW">Day -->
<!-- 								of week</label> -->
<!-- 						</p> -->
<!-- 						<p class="i-checks"> -->
<!-- 							<label> <input type="radio" name="smsChartOption" -->
<!-- 								id="smsChartOptionPD" ng-model="smsDoW" value="false"> -->
<!-- 								<i></i></label> <label for="smsChartOptionPD">Per day</label> -->
<!-- 						</p> -->
<!-- 					</div> -->
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>
<!-- 	<div class="row"> -->
<!-- 		<div class="col-lg-12"> -->
<!-- 			<ul class="roamingtrends-optionlist"> -->
<!-- 				<li> -->
<!-- 					<div class="btn-group arpv-btns" data-toggle="buttons"> -->
<!-- 						<label class="btn btn-primary active" ng-click="clearRoamingCategoryTempFilter()"> <input type="radio" -->
<!-- 							name="options" id="allarpv" checked > All -->
<!-- 						</label> <label class="btn btn-primary" ng-click="getSilentCustomers()"> <input type="radio" -->
<!-- 							name="options" id="higharpv"> Silent -->
<!-- 						</label> <label class="btn btn-primary" ng-click="getValueCustomers()"> <input type="radio" -->
<!-- 							name="options" id="medarpv" > Value -->
<!-- 						</label> <label class="btn btn-primary" ng-click="getPremiumCustomers()"> <input type="radio" -->
<!-- 							name="options" id="lowarpv" > Premium -->
<!-- 						</label> -->
<!-- 					</div> -->
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 					<div class="btn-group arpv-btns" data-toggle="buttons"> -->
<!-- 						<label class="btn btn-primary active" ng-click='clearARPUTempFilter()'> <input type="radio" -->
<!-- 							name="options" id="allarpv" checked > All -->
<!-- 						</label> <label class="btn btn-primary" ng-click="getHighARPUCustomers()"> <input type="radio" -->
<!-- 							name="options" id="higharpv" > High ARPU -->
<!-- 						</label> <label class="btn btn-primary" ng-click="getMedARPUCustomers()"> <input type="radio" -->
<!-- 							name="options" id="medarpv" > Med ARPU -->
<!-- 						</label> <label class="btn btn-primary" ng-click="getLowARPUCustomers()"> <input type="radio" -->
<!-- 							name="options" id="lowarpv"  > Low ARPU -->
<!-- 						</label> -->
<!-- 					</div> -->
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 					<div class="btn-group arpv-btns" data-toggle="buttons"> -->
<!-- 						<label class="btn btn-primary active" ng-click='clearPaymentTypeTempFilter()'> <input type="radio" -->
<!-- 							name="options" id="allpre" checked > All -->
<!-- 						</label> <label class="btn btn-primary" ng-click="getPrepaidCustomers()"> <input type="radio" -->
<!-- 							name="options" id="Prepaid" > Prepaid -->
<!-- 						</label> <label class="btn btn-primary" ng-click="getPostpaidCustomers()"> <input type="radio" -->
<!-- 							name="options" id="Postpaid"> Postpaid -->
<!-- 						</label> -->
<!-- 					</div> -->
<!-- 				</li> -->
<!-- 			</ul> -->
<!-- 		</div> -->
<!-- 	</div> -->
</div>
 <div ng-controller="RoamingStatisticsControllerTrend">
    <div class="row dashboard-statics">
      <div class="col-lg-6">
        <section class="panel">
          <div class="symbol lightblue">
            <i class="roamers-icon"></i>
            <p>Roamers</p>
          </div>
          <div class="value">
            <p class="statics-num lightblue-text">{{totalRoamer}}</p>
            <ul class="statics-subnum">
              <li class="cust-tooltip-dn" original-title="Projected: 350">
                <p class="subnum-text">Silent</p>
                <p class="subnum-number"><span class="arr-space"><img src="images/down-icon.png" ></span>{{silentRoamer}}</p>
              </li>
              <li>
                <p class="subnum-text">Value</p>
                <p class="subnum-number">{{valueRoamer}}</p>
              </li>
              <li>
                <p class="subnum-text">Premium</p>
                <p class="subnum-number">{{premiumRoamer}}</p>
              </li>
            </ul>
          </div>
        </section>
      </div>
      <div class="col-lg-6">
        <section class="panel">
          <div class="symbol purple">
            <i class="mo-icon"></i>
            <p>MO(min)</p>
          </div>
          <div class="value">
            <p class="statics-num purple-text">{{totalMo}}</p>
            <ul class="statics-subnum">
              <li class="cust-tooltip-dn" original-title="Projected: 81">
                <p class="subnum-text">Home</p>
                <p class="subnum-number"><span class="arr-space"><img src="images/down-icon.png"></span>{{homeMo}}</p>
              </li>
              <li>
                <p class="subnum-text">Local</p>
                <p class="subnum-number">{{localMo}}</p>
              </li>
              <li>
                <p class="subnum-text">Intl</p>
                <p class="subnum-number"><span class="arr-space"><img src="images/up-icon.png"></span>{{intlMo}}</p>
              </li>
            </ul>
          </div>
        </section>
      </div>
    </div>
    <div class="row dashboard-statics">
      <div class="col-lg-3">
        <section class="panel">
          <div class="symbol light-green">
            <i class="mt-icon"></i>
            <p>MT(Min)</p>
          </div>
          <div class="value">
            <p class="statics-num light-green-text">{{totalMt}}</p>
          </div>
        </section>
      </div>
      <div class="col-lg-3">
        <section class="panel">
          <div class="symbol light-orange">
            <i class="data-icon"></i>
            <p>Data(MB)</p>
          </div>
          <div class="value">
            <p class="statics-num light-orange-text">{{totalData}}</p>
          </div>
        </section>
      </div>
      <div class="col-lg-3">
        <section class="panel">
          <div class="symbol yellow">
            <i class="sms-icon"></i>
            <p>SMS</p>
          </div>
          <div class="value">
            <p class="statics-num yellow-text">{{totalSms}}</p>
          </div>
        </section>
      </div>
    </div>
   </div> <!-- End of RoamingStatisticsController  -->
   
<div id="roamer-ft-zoom" class="charts-zoom"
	ng-controller="ZoomController">
	<div width="100%" height="450">
	
		<div class="back-top">
			<div class="" >
				<div class="topChart-angle pull-left extrlightblue clearfix" ng-if='viewZoomScale' >
			      <input type="button" id="rom-opt-d" value="Log Scale" class="angle_btn " 
			      ng-class="{'active-scale' : zoomLogScale == 'true'}" ng-click="changeLogScale('true')">
			      <input type="button" id="rom-opt-c" value="Linear Scale" class="angle_btn"  
			      ng-class="{'active-scale' : zoomLogScale == 'false'}" ng-click="changeLogScale('false')" >
			    </div>
				<highchart id="roamerChartZ" config="chartConfig" class=""></highchart>
			</div>
		</div>
	</div>
	<button type="button" id="close-btn" class="tre-close-btn"
		data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
	</button>
</div>
