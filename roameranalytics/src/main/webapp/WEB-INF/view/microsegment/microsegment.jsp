	<div class="row dashboard-top">
		<div class="col-lg-3">
			<h1 class="maincontent-heading">MICRO SEGMENT</h1>
		</div>
		<div class="col-lg-7">

	</div>
		<div class="col-lg-2">
			<div class="commentshare-icon">
			<span class="dropdown micro-setting-area"> <a
				href="javascript:void(0)" class="micro-setting-icon cust-tooltip"
				data-toggle="dropdown" original-title="Settings"></a>
				<div
					class="dropdown-menu pull-right comment-dropdown popover bottom stay-dd">
					<div class="arrow"></div>
					<div class="commentdd-content">
						<p class="trackdd-title">Attribute measure</p>
						<ul class="micro-map-setting">
							<li>
								<p class="i-checks">
									<label> <input type="radio" name="project" id="roamers" ng-click="changeMSAttributeMeasure('roamers')"
										class="all-blue-map" value="" checked> <i></i></label> <label
										for="roamers">roamers</label>
								</p>
							</li>
							<li >
								<p class="i-checks" >
									<label > <input type="radio" name="project" id="mo"  ng-click="changeMSAttributeMeasure('mo')"
										value="mo" > <i></i></label> <label for="mo">MO
										(Min)</label>
								</p>
							</li>
							<li>
								<p class="i-checks">
									<label> <input type="radio" name="project" id="mt" ng-click="changeMSAttributeMeasure('mt')"
										class="all-blue-map" value="mt"> <i></i></label> <label for="mt">MT
										(Min)</label>
								</p>
							</li>
							<li>
								<p class="i-checks">
									<label> <input type="radio" name="project" id="Data" ng-click="changeMSAttributeMeasure('data')"
										class="all-blue-map" value="data"> <i></i></label> <label
										for="Data">Data</label>
								</p>
							</li>

							<div class="compare_btn clearfix">
								<button type="button" class="btn btn-primary pull-left" ng-click='applyMicrosegmentSettings()'>Apply</button>
							</div>
						</ul>
					</div>

				</div>
			</span> <span class="dropdown" style="display:none"> <a href="javascript:void(0)"
					class="greycomment-icon cust-tooltip" data-toggle="dropdown"
					original-title="Comment"></a>
					<div
						class="dropdown-menu pull-right comment-dropdown popover bottom stay-dd">
						<div class="arrow"></div>
						<div class="commentdd-content">
							<p class="trackdd-title">Add Comment</p>
							<textarea class="track-textarea"
								placeholder="Add your comments here"></textarea>
							<button type="button" class="btn btn-primary">Save</button>
							<button type="button" class="btn btn-primary unactive">Cancel</button>
						</div>
						<div class="commentdd-footer">
							Share With <a href="javascript:void(0)" class="share-public"></a>
							<a href="javascript:void(0)" class="share-frnds active"></a> <a
								href="javascript:void(0)" class="share-onlyme"></a>
						</div>
					</div>
				</span> <span class="dropdown" style="display:none"><a href="javascript:void(0)"
					class="microtrack-icon cust-tooltip" data-toggle="dropdown"
					original-title="Track"> </a>
					<div
						class="dropdown-menu pull-right track-dropdown popover bottom stay-dd">
						<div class="arrow"></div>
						<div class="tracker-area clearfix">
							<h2>Create Trcaker</h2>
							<div class="tracker-name-box clearfix">
								<p class="trackdd-title">Track micro segment as</p>
								<input type="text" class="track-input" placeholder="Enter Name" />
							</div>
							<p class="trackdd-title">Compare between</p>
							<div class="compare-between">
								<input type="text" class="track-input"
									placeholder="Current and Last month" /> <input type="text"
									class="track-input" placeholder="Current month and Last year" />
								<input type="text" class="track-input compare-custom"
									placeholder="Custom" />
								<div class="pull-right compare_btn clearfix">
									<button type="button" class="btn btn-primary pull-left">Save</button>
								</div>
							</div>
							<div class="compare-custom-box">
								<div class="compare-bet-area clearfix">
									<div class="compare-month pull-left">
										<p>Month</p>
										<select id="basic" class="compare-month-select select">
											<option value="">1</option>
											<option value="tray1">2</option>
											<option value="tray2">3</option>
											<option value="tray3">4</option>
											<option value="tray4">5</option>
											<option value="tray5">6</option>
											<option value="tray6">7</option>
											<option value="tray7">8</option>
											<option value="tray8">9</option>
											<option value="tray9">10</option>
											<option value="tray10">11</option>
											<option value="tray11">12</option>
											<option value="tray12">13</option>
											<option value="tray13">14</option>
											<option value="tray14">15</option>
											<option value="tray15">16</option>
											<option value="tray16">17</option>
											<option value="tray17">18</option>
										</select>
									</div>
									<div class="compare-month pull-left">
										<p>Year</p>
										<select id="basic" class="compare-month-select select">
											<option value="">2014</option>
											<option value="tray1">2013</option>
											<option value="tray2">2012</option>
											<option value="tray3">2011</option>
											<option value="tray4">2010</option>
											<option value="tray5">2009</option>
										</select>
									</div>
								</div>
								<p class="trackdd-title">&</p>
								<div class="compare-bet-area clearfix">
									<div class="compare-month pull-left">
										<p>Month</p>
										<select id="basic" class="compare-month-select select">
											<option value="">1</option>
											<option value="tray1">2</option>
											<option value="tray2">3</option>
											<option value="tray3">4</option>
											<option value="tray4">5</option>
											<option value="tray5">6</option>
											<option value="tray6">7</option>
											<option value="tray7">8</option>
											<option value="tray8">9</option>
											<option value="tray9">10</option>
											<option value="tray10">11</option>
											<option value="tray11">12</option>
											<option value="tray12">13</option>
											<option value="tray13">14</option>
											<option value="tray14">15</option>
											<option value="tray15">16</option>
											<option value="tray16">17</option>
											<option value="tray17">18</option>
										</select>
									</div>
									<div class="compare-month pull-left">
										<p>Year</p>
										<select id="basic" class="compare-month-select select">
											<option value="">2014</option>
											<option value="tray1">2013</option>
											<option value="tray2">2012</option>
											<option value="tray3">2011</option>
											<option value="tray4">2010</option>
											<option value="tray5">2009</option>
										</select>
									</div>
									<div class="pull-right compare_btn clearfix">
										<button type="button"
											class="btn btn-primary unactive pull-left">
											<i class="back-arrow"></i>Back
										</button>
										<button type="button" class="btn btn-primary pull-left">Save</button>
									</div>
								</div>
							</div>
						</div>
					</div> </span>
			</div>
		</div>
	</div>
	
	 <div class="row dashboard dashboard-scroll">
      <div class="col-lg-12">
     		 <div class="tag-div" ng-repeat="filter in countryCategories">
				{{filter.name}} <a href
					ng-click='removeCountryCategoryFilter(filter.id,filter.identifier)' class="delete-tag"></a>
			</div>
			<div class="tag-div" ng-repeat="filter in countriesFromList">
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
  </div>
	
	
	<div class="row microsegment-charts" ng-controller="MicroSegmentController">
		<div class="col-lg-9 clearfix">
			<div class="microchart-panel" ng-repeat="graph in graphToBeShown">
				<div class="segment-charts">
					<div class="microchart-heading">
						{{title[graph.title]}} <a href="javascript:void(0)" style="display:none"
							class="bookmark-unactive cust-tooltip" original-title="Bookmark"></a>
					</div>
					<div id="bar-chart-{{graph.id}}"></div>
					<donutchart id='donut-{{graph.id}}' class="big-donutchart loading" attributeid='{{graph.attributeId}}' columnname="{{graph.column}}" columntype="{{graph.columnType}}" charttype='{{graph.chartType}}' chartname="{{graph.title}}" daterange='daterange'/>
					<div id="column-chart-{{graph.id}}"></div>
				</div>
				<div class="bookmark-panel">
					<a href="javascript:void(0)" class="close-bookmark" style="display:none"><i
						class="fa close-icon"></i></a>
					<div class="bookmarkpnl-content">
						<div class="clearfix">
							<p class="bookmarkpnl-title">Select Bookmark Tray</p>
							<select id="basic" class="select">
								<option value="">July Business Roamers</option>
								<option value="tray1">Tray 2</option>
								<option value="tray2">Tray 3</option>
								<option value="tray3">Tray 4</option>
								<option value="tray4">Tray 5</option>
								<option value="tray5">Tray 6</option>
							</select> <input type="button" class="select-btn" value="Select">
						</div>
						<p class="or-text">OR</p>
						<div class="clearfix">
							<p class="bookmarkpnl-title">Create New Tray</p>
							<input type="text" class="bookmarkpnl-textbox" value=""
								placeholder="Create New"> <input type="button"
								class="select-btn" value="Create">
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-lg-3 dashboard-statics microsegment-statics">
		<div class="section-backdrop">
        <div class="loader"></div>
			</div>
		
		<div ng-controller="RoamingStatisticsControllerMicrosegment">
		
        <section class="panel">

          <div class="symbol lightblue">

            <i class="seg-roamers-icon"></i>

            <p>Roamers</p>

          </div>

          <div class="value mt6">

            <ul class="statics-subnum">

              <li>

                <p class="subnum-text">Silent</p>

                <p class="subnum-number"><span class="arr-space"><img style="display:none" src="images/down-icon.png"></span>{{silentRoamer}}</p>

              </li>

              <li>

                <p class="subnum-text">Value</p>

                <p class="subnum-number">{{valueRoamer}}</p>

              </li>

              <li>

                <p class="subnum-text"  title="Premium">Prem</p>

                <p class="subnum-number">{{premiumRoamer}}</p>

              </li>

            </ul>

            <p class="statics-num lightblue-text">{{totalRoamer}}</p>

          </div>

        </section>

        <section class="panel">

          <div class="symbol purple pt6">

            <i class="seg-mo-icon"></i>

            <p>MO <br>

              (Min)</p>

          </div>

          <div class="value mt6">

          <ul class="statics-subnum">

              <li>

                <p class="subnum-text">Home</p>

                <p class="subnum-number"><span class="arr-space"><img style="display:none" src="images/down-icon.png"></span>{{homeMo}}</p>

              </li>

              <li>

                <p class="subnum-text">Local</p>

                <p class="subnum-number">{{localMo}}</p>

              </li>

              <li>

                <p class="subnum-text" title="International">Intl</p>

                <p class="subnum-number"><span class="arr-space"><img style="display:none" src="images/up-icon.png"></span>{{intlMo}}</p>

              </li>

            </ul>

            <p class="statics-num purple-text">{{totalMo}}</p>

          </div>

        </section>

         <section class="panel">

          <div class="symbol light-green pt6">

            <i class="seg-mt-icon"></i>

            <p>MT <br>

              (Min)</p>

          </div>

          <div class="value">

            <p class="statics-num light-green-text">{{totalMt}}</p>

          </div>

        </section>

        <section class="panel">

          <div class="symbol light-orange">

            <i class="data-icon"></i>

            <p>Data (MB)</p>

          </div>

          <div class="value">

            <p class="statics-num light-orange-text">{{totalData}}</p>

          </div>

        </section>

        <section class="panel">

          <div class="symbol yellow">

            <i class="sms-icon"></i>

            <p>SMS</p>

          </div>

          <div class="value">

            <p class="statics-num yellow-text">{{totalSms}}</p>

          </div>

        </section>
     </div><!-- end of  RoamingStatisticsControllerTrend -->

        <div class="panel space-blank">

       

          <div id="accordion-comm" class="panel-group" style="display:none">

            <div class="panel accor-color panel-default mt20">

              <div class="comment-panel greenpanel-head clearfix">

                <div class="pull-left comment-title-panel left_space_blank">

                  <span><i class="comment-icon"></i></span>

                  <ul class="commentpanel-title">

                    <li class="panel-text mt10">Comments 2 (1)</li>

                  </ul>

                </div>

                <div class="pull-right right_space_blank">

                  <div class="pull-right">

                    <span><a href="#uponCollapseTwo" data-toggle="collapse" data-target="#uponCollapseTwo" class="collapsed"><i class="collapse-icon"></i></a>

                    </span>

                  </div>

                </div>

              </div>

              <div id="uponCollapseTwo" class="panel-collapse collapse">

                <table class="comment-list comment-pagelist">

                  <tr>

                    <td><img src="images/user-img1.png" alt="" /></td>

                    <td><div class="clearfix">

                        <p class="comment-name">Sangeeta Sharma</p>

                        <i class="comment-date">Jun 25, 2014 </i>

                      </div>

                      <p class="comment-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed deius</p></td>

                    <td><i class="public-icon cust-tooltip" original-title="Public"></i>

                      <span class="dropdown"><a href="javascript:void(0)" class="more-icon cust-tooltip" data-toggle="dropdown" original-title="More"></a>

                      <ul class="dropdown-menu popover left pull-right more-dd" role="menu" aria-labelledby="dLabel">

                        <div class="arrow"></div>

                        <li><a href="#">Reply</a>

                        </li>

                        <li><a href="#">Mark as important</a>

                        </li>

                        <li><a href="#">Delete</a>

                        </li>

                      </ul>

                      </span></td>

                  </tr>

                  <tr>

                    <td><img src="images/user-img1.png" alt="" /></td>

                    <td><div class="clearfix">

                        <p class="comment-name">Sangeeta Sharma</p>

                        <i class="comment-date">Jun 25, 2014 </i>

                      </div>

                      <p class="comment-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed deius</p></td>

                    <td><i class="public-icon cust-tooltip" original-title="Public"></i>

                      <span class="dropdown"><a href="javascript:void(0)" class="more-icon cust-tooltip" data-toggle="dropdown" original-title="More"></a>

                      <ul class="dropdown-menu popover left pull-right more-dd" role="menu" aria-labelledby="dLabel">

                        <div class="arrow"></div>

                        <li><a href="#">Reply</a>

                        </li>

                        <li><a href="#">Mark as important</a>

                        </li>

                        <li><a href="#">Delete</a>

                        </li>

                      </ul>

                      </span></td>

                  </tr>

                  <tr class="comment-add-area" style="display: none;">

                  		<td colspan="4"><div class="comm-text-area"><textarea placeholder="Add your comments here" class="track-textarea"></textarea> <br> <button type="button" class="btn btn-primary-comm pull-right clearfix">Submit</button></div></td>

                  </tr>

                </table>

              </div>

            </div>

          </div>

        

        </div>

      </div><!-- end of  col-lg-3 dashboard-statics microsegment-statics -->
		
		
		
	</div>

