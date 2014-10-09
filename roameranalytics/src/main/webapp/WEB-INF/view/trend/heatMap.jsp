<div class="row dashboard-top">
      <div class="col-lg-3">
        <h1 class="maincontent-heading">Home</h1>
      </div>
      <div class="col-lg-7">
		<div class="tag-div" ng-repeat="filter in filters.countries">
				{{filter.name}} <a href
					ng-click='removeCounryFilter(filter.id,true)' class="delete-tag"></a>
			</div>
			<div class="tag-div" ng-repeat="filter in filters.personas">
				{{filter.name}} <a href
					ng-click='removePersonaFilter(filter.id,true)' class="delete-tag"></a>
			</div>
			<span ng-repeat="(key, value) in filters.attributes">
				<div class="tag-div" ng-repeat="filter in value">
					{{filter.name}} <a href
						ng-click='removeAttributeFilter(key,filter.attrId, filter.catId,true)'
						class="delete-tag"></a>
				</div>
			</span>
		
	</div>
      <div class="col-lg-2">
        <div class="commentshare-icon" style="display:none">
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
    
     <div ng-controller="HeatMapControllerHome">
    <div class="row map-view clearfix">
      <div class="col-lg-12 clearfix">
     
        <div class="map-content clearfix">
          <ul class="map-tabs">
            <li>
              <p class="i-checks">
                <label>
                  <input type="radio" name="project" id="usage" value="usage" ng-model="mapUnit" checked>
                  <i></i></label>
                <label for="usage">Expected Usage</label>
              </p>
            </li>
            <li>
              <p class="i-checks">
                <label>
                  <input type="radio" name="project" id="roamers" class="all-blue-map" value="roamers" ng-model="mapUnit">
                  <i></i></label>
                <label for="roamers">roamers</label>
              </p>
            </li>
            <li>
              <p class="i-checks">
                <label>
                  <input type="radio" name="project" id="mt" class="all-blue-map" value="mt" ng-model="mapUnit">
                  <i></i></label>
                <label for="mt">mt (min)</label>
              </p>
            </li>
            <li>
              <p class="i-checks">
                <label>
                  <input type="radio" name="project" id="mo" class="all-blue-map" value="mo" ng-model="mapUnit">
                  <i></i></label>
                <label for="mo">mo (min)</label>
              </p>
            </li>
            <li>
              <p class="i-checks">
                <label>
                  <input type="radio" name="project" id="data" class="all-blue-map" value="data" ng-model="mapUnit" ng-model="mapUnit">
                  <i></i></label>
                <label for="data">data (mb)</label>
              </p>
            </li>
            
          </ul>
          
         
          <div class="map-container">
          <div id="map-container">
            <div class="map-loading">
              <i class="icon-spinner icon-spin icon-large"></i> Loading data from Google Spreadsheets... </div>
          </div>
          
          <div id="map-container-roamer" class="clearfix">
            <div class="map-loading">
              <i class="icon-spinner icon-spin icon-large"></i> Loading data from Google Spreadsheets... </div>
          </div>
          </div>
          </div>
          
        
        </div>
      </div>
   
    
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
                <p class="subnum-number"><span class="arr-space"><img style="display:none" src="images/down-icon.png" ></span>{{silentRoamer}}</p>
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
                <p class="subnum-number"><span class="arr-space"><img style="display:none" src="images/down-icon.png"></span>{{homeMo}}</p>
              </li>
              <li>
                <p class="subnum-text">Local</p>
                <p class="subnum-number">{{localMo}}</p>
              </li>
              <li>
                <p class="subnum-text">Intl</p>
                <p class="subnum-number"><span class="arr-space"><img style="display:none" src="images/up-icon.png"></span>{{intlMo}}</p>
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

    <div class="top10chart-view-home">
      <div class="row">
        <div class="col-lg-3">
          <div class="flip">
            <div class="card">
              <div class="face front">
                <div class="top10chart-panel">
                  <div class="bubble-label-chart">
                    <div class="visfront">
                      <div id="vis"></div>
                    </div>
                    <div class="visback">
                      <div id="vis1"></div>
                    </div>
                    <div class="top10-chart-btn lightblue-checked clearfix">
                      <p class="i-checks">
                        <label>
                        <input type="checkbox" name="bubble-chart1" id="bubble-chart1" value="" class="heavy-hitter-opt">
                        <i></i></label>
                        <label for="bubble-chart1" class="">Remove Heavy Hitter</label>
                        </p>
                    </div>
                  </div>
                  <div class="top10chart-footer lightblue clearfix">
                    <p class="chart-name pull-left">Roamers</p>
                    <a href="javascript:void(0)" class="viewtrend-link darkblue pull-right"><img src="images/rm-chart.png" ></a>
                  </div>
                </div>
              </div>
              <div class="face back">
                <div class="top10chart-panel">
                  <div class="linechart-box">
                    <div id="container"></div>
                    <div class="top10-chart-btn lightblue-checked">
                       <p class="i-checks">
                        <label>
                        <input type="checkbox" name="roamer-chart" id="roamer-chart" value="" class="roamer-chart">
                        <i></i></label>
                        <label for="roamer-chart" class="">Remove Heavy Hitter</label>
                        </p>
                    </div>
                  </div>
                  <div class="top10chart-footer lightblue clearfix">
                    <p class="chart-name pull-left">Roamers</p>
                    <a href="javascript:void(0)" class="viewtrend-link darkblue pull-right"><img src="images/mt-chart.png" ></a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-3">
          <div class="flip">
            <div class="card">
              <div class="face front">
                <div class="top10chart-panel">
                  <div class="bubble-label-chart">
                    <div class="visfront">
                      <div id="vis2"></div>
                    </div>
                    <div class="visback">
                      <div id="vis2n"></div>
                    </div>
                    <div class="top10-chart-btn purple-checked clearfix">
                         <p class="i-checks">
                        <label>
                        <input type="checkbox" name="bubble-chart2" id="bubble-chart2" value="" class="heavy-hitter-opt" >
                        <i></i></label>
                        <label for="bubble-chart2" class="">Remove Heavy Hitter</label>
                        </p>
                    </div>
                  </div>
                  <div class="top10chart-footer purple clearfix">
                    <p class="chart-name pull-left">MO(Min)</p>
                    <a href="javascript:void(0)" class="viewtrend-link purple-dark pull-right"><img src="images/rm-chart.png" ></a>
                  </div>
                </div>
              </div>
              <div class="face back">
                <div class="top10chart-panel">
                  <div class="linechart-box">
                    <div id="container3"></div>
                    <div class="top10-chart-btn purple-checked">
                      <p class="i-checks">
                        <label>
                        <input type="checkbox" name="mo-chart" id="mo-chart" value="" class="mo-chart">
                        <i></i></label>
                        <label for="mo-chart" class="">Remove Heavy Hitter</label>
                        </p>
                    </div>
                  </div>
                  <div class="top10chart-footer purple clearfix">
                    <p class="chart-name pull-left">MO (Min)</p>
                    <a href="javascript:void(0)" class="viewtrend-link purple-dark pull-right"><img src="images/mt-chart.png" ></a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-3">
          <div class="flip">
            <div class="card">
              <div class="face front">
                <div class="top10chart-panel">
                  <div class="bubble-label-chart">
                    <div class="visfront">
                      <div id="vis3"></div>
                    </div>
                    <div class="visback">
                      <div id="vis3n"></div>
                    </div>
                    <div class="top10-chart-btn light-green-checked clearfix">
                       <p class="i-checks">
                        <label>
                        <input type="checkbox" name="bubble-chart3" id="bubble-chart3" value="" class="heavy-hitter-opt">
                        <i></i></label>
                        <label for="bubble-chart3" class="">Remove Heavy Hitter</label>
                        </p>
                    </div>
                  </div>
                  <div class="top10chart-footer light-green clearfix">
                    <p class="chart-name pull-left">MT(Min)</p>
                    <a href="javascript:void(0)" class="viewtrend-link dark-green pull-right"><img src="images/rm-chart.png" ></a>
                  </div>
                </div>
              </div>
              <div class="face back">
                <div class="top10chart-panel">
                  <div class="linechart-box">
                    <div id="container2"></div>
                    <div class="top10-chart-btn light-green-checked">
                     <p class="i-checks">
                        <label>
                        <input type="checkbox" name="mt-chart" id="mt-chart" value="" class="mt-chart">
                        <i></i></label>
                        <label for="mt-chart" class="">Remove Heavy Hitter</label>
                        </p>
                    </div>
                  </div>
                  <div class="top10chart-footer light-green clearfix">
                    <p class="chart-name pull-left">MT (Min)</p>
                    <a href="javascript:void(0)" class="viewtrend-link dark-green pull-right"><img src="images/mt-chart.png" ></a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-3">
          <div class="flip">
            <div class="card">
              <div class="face front">
                <div class="top10chart-panel">
                  <div class="bubble-label-chart">
                    <div class="visfront">
                      <div id="vis4"></div>
                    </div>
                    <div class="visback">
                      <div id="vis4n"></div>
                    </div>
                    <div class="top10-chart-btn light-orange-checked clearfix">
                       <p class="i-checks">
                        <label>
                        <input type="checkbox" name="bubble-chart4" id="bubble-chart4" value="" class="heavy-hitter-opt" >
                        <i></i></label>
                        <label for="bubble-chart4" class="">Remove Heavy Hitter</label>
                        </p>
                    </div>
                  </div>
                  <div class="top10chart-footer light-orange clearfix">
                    <p class="chart-name pull-left">Data(MB)</p>
                    <a href="javascript:void(0)" class="viewtrend-link dark-orange-mr pull-right"><img src="images/rm-chart.png" ></a>
                  </div>
                </div>
              </div>
              <div class="face back">
                <div class="top10chart-panel">
                  <div class="linechart-box">
                    <div id="container4"></div>
                    <div class="top10-chart-btn light-orange-checked">
                      <p class="i-checks">
                        <label>
                        <input type="checkbox" name="data-chart" id="data-chart" value="" class="data-chart">
                        <i></i></label>
                        <label for="data-chart" class="">Remove Heavy Hitter</label>
                        </p>
                    </div>
                  </div>
                  <div class="top10chart-footer light-orange clearfix">
                    <p class="chart-name pull-left">Data (MB)</p>
                    <a href="javascript:void(0)" class="viewtrend-link dark-orange-mr pull-right"><img src="images/mt-chart.png" ></a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
        	<form id="top10csvform" action="./getTop10CSV" method="post">
        		<a href="javascript:document.getElementById('top10csvform').submit();" class="top-table-btn-link"><span><i class="top-table-icon"></i></span>Top 10 Table</a>
        		<input type="hidden" value="{{top10CsvText}}" name="top10csvtext"/>
        	</form>
        </div>
      </div>
    </div>
    </div><!-- heatmapcontroller ends -->
    
    <div class="row" style="display:none">
      <div class="col-lg-6">
        <div class="panel">
          <div class="redpanel-head">
            <span><i class="alert-icon"></i></span>
            <p>Alert</p>
          </div>
          <ul class="alert-list">
            <li>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore qualconsequat. </li>
            <li>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore qualconsequat. </li>
            <li>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore qualconsequat. </li>
          </ul>
        </div>
      </div>
      <div class="col-lg-6">
        <div class="panel">
          <div class="commentpanel-head">
            <span><i class="comment-icon"></i></span>
            <p>Comment</p>
          </div>
          <table class="comment-list">
            <tr>
              <td><div class="clearfix">
                  <p class="comment-name">Sangeeta Sharma</p>
                  <i class="comment-date">Jun 25, 2014 </i>
                </div>
                <p class="comment-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed deius</p></td>
              <td><i class="public-icon cust-tooltip" original-title="Public"></i>
                <span class="dropdown"><a href="javascript:void(0)" class="more-icon cust-tooltip" data-toggle="dropdown" original-title="More"></a>
                <ul class="dropdown-menu popover left pull-right more-dd" role="menu" aria-labelledby="dLabel">
                  <div class="arrow"></div>
                  <li><a href="#">Edit</a>
                  </li>
                  <li><a href="#">Delete</a>
                  </li>
                </ul>
                </span></td>
            </tr>
            <tr>
              <td><div class="clearfix">
                  <p class="comment-name">Sangeeta Sharma</p>
                  <i class="comment-date">Jun 25, 2014 </i>
                </div>
                <p class="comment-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed deius</p></td>
              <td><i class="friends-icon cust-tooltip" original-title="Friends"></i>
                <span class="dropdown"><a href="javascript:void(0)" class="more-icon cust-tooltip" data-toggle="dropdown" original-title="More"></a>
                <ul class="dropdown-menu popover left pull-right more-dd" role="menu" aria-labelledby="dLabel">
                  <div class="arrow"></div>
                  <li><a href="#">Edit</a>
                  </li>
                  <li><a href="#">Delete</a>
                  </li>
                </ul>
                </span></td>
            </tr>
            <tr>
              <td><div class="clearfix">
                  <p class="comment-name">Sangeeta Sharma</p>
                  <i class="comment-date">Jun 25, 2014 </i>
                </div>
                <p class="comment-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed deius</p></td>
              <td><i class="public-icon cust-tooltip" original-title="Public"></i>
                <span class="dropdown"><a href="javascript:void(0)" class="more-icon cust-tooltip" data-toggle="dropdown" original-title="More"></a>
                <ul class="dropdown-menu popover left pull-right more-dd" role="menu" aria-labelledby="dLabel">
                  <div class="arrow"></div>
                  <li><a href="#">Edit</a>
                  </li>
                  <li><a href="#">Delete</a>
                  </li>
                </ul>
                </span></td>
            </tr>
          </table>
        </div>
      </div>
    </div>