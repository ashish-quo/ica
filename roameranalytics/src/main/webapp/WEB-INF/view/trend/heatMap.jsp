<div class="row dashboard-top">
      <div class="col-lg-3">
        <h1 class="maincontent-heading">Home</h1>        
      </div>
      <div class="col-lg-7">
        <div class="tag-div">Nigeria <a href="javascript:void(0)" class="delete-tag"></a>
        </div>
        <div class="tag-div">Business <a href="javascript:void(0)" class="delete-tag"></a>
        </div>
        <div class="tag-div">Premium Phone <a href="javascript:void(0)" class="delete-tag"></a>
        </div>
      </div>
      <div class="col-lg-2">
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
    <div class="row map-view">
      <div class="col-lg-12">
        <div class="map-content">         
          <ul class="map-tabs">
            <li>
              <p class="i-checks">
                <label>
                  <input type="radio" name="project" id="usage" value="" checked>
                  <i></i></label>
                <label for="usage">Expected Usage</label>
              </p>
            </li>
            <li>
              <p class="i-checks">
                <label>
                  <input type="radio" name="project" id="roamers" value="">
                  <i></i></label>
                <label for="roamers">roamers</label>
              </p>
            </li>
            <li>
              <p class="i-checks">
                <label>
                  <input type="radio" name="project" id="mt" value="">
                  <i></i></label>
                <label for="mt">mt (min)</label>
              </p>
            </li>
            <li>
              <p class="i-checks">
                <label>
                  <input type="radio" name="project" id="mo" value="">
                  <i></i></label>
                <label for="mo">mo (min)</label>
              </p>
            </li>
            <li>
              <p class="i-checks">
                <label>
                  <input type="radio" name="project" id="data" value="">
                  <i></i></label>
                <label for="data">data (mb)</label>
              </p>
            </li>
            <strong></strong>
          </ul>
          
<div id="map-container">
            <div class="map-loading">
              <i class="icon-spinner icon-spin icon-large"></i> Loading data from Google Spreadsheets... </div>
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
            <p class="statics-num lightblue-text">425</p>
            <ul class="statics-subnum">
              <li class="cust-tooltip-dn" original-title="Projected: 350">
                <p class="subnum-text">Silent</p>
                <p class="subnum-number"><span class="arr-space"><img src="images/down-icon.png" ></span>300</p>
              </li>
              <li>
                <p class="subnum-text">Value</p>
                <p class="subnum-number">75</p>
              </li>
              <li>
                <p class="subnum-text">Premium</p>
                <p class="subnum-number">50</p>
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
            <p class="statics-num purple-text">425</p>
            <ul class="statics-subnum">
              <li class="cust-tooltip-dn" original-title="Projected: 81">
                <p class="subnum-text">Home</p>
                <p class="subnum-number"><span class="arr-space"><img src="images/down-icon.png"></span>50</p>
              </li>
              <li>
                <p class="subnum-text">Local</p>
                <p class="subnum-number">350</p>
              </li>
              <li>
                <p class="subnum-text">Intl.</p>
                <p class="subnum-number"><span class="arr-space"><img src="images/up-icon.png"></span>25</p>
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
            <p class="statics-num light-green-text">987</p>
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
            <p class="statics-num light-orange-text">1095</p>
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
            <p class="statics-num yellow-text">317</p>
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
                  <div class="visfront"><div id="vis"></div></div>
                  <div class="visback"><div id="vis1"></div></div>
                  <div class="pull-right top10-chart-btn extrlightblue clearfix">
                    <button class="show-hea-hitter angle_btn active-scale">Show Heavy Hitter</button>
                    <button class="remove-hea-hitter angle_btn">Remove Heavy Hitter</button>
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
                    <div class="pull-right top10-chart-btn extrlightblue">
					<button id="b2" class="angle_btn active-scale">Show Heavy Hitter</button>                    
                    <button id="b1" class="angle_btn">Remove Heavy Hitter</button>
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
                  <div class="visfront"><div id="vis2"></div></div>
                  <div class="visback"><div id="vis2n"></div></div>
                  <div class="pull-right top10-chart-btn purple clearfix">
                    <button class="show-hea-hitter angle_btn active-scale">Show Heavy Hitter</button>
                    <button class="remove-hea-hitter angle_btn">Remove Heavy Hitter</button>
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
                    <div class="pull-right top10-chart-btn purple">
					<button id="b6" class="angle_btn active-scale">Show Heavy Hitter</button>
                    <button id="b5" class="angle_btn">Remove Heavy Hitter</button>
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
                  <div class="visfront"><div id="vis3"></div></div>
                  <div class="visback"><div id="vis3n"></div></div>
                  <div class="pull-right top10-chart-btn light-green clearfix">
                    <button class="show-hea-hitter angle_btn active-scale">Show Heavy Hitter</button>
                    <button class="remove-hea-hitter angle_btn">Remove Heavy Hitter</button>
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
                     <div class="pull-right top10-chart-btn light-green"> 
					<button id="b4" class="angle_btn active-scale">Show Heavy Hitter</button>                   
                    <button id="b3" class="angle_btn">Remove Heavy Hitter</button>
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
                  <div class="visfront"><div id="vis4"></div></div>
                  <div class="visback"><div id="vis4n"></div></div>
                  <div class="pull-right top10-chart-btn light-orange clearfix">
                    <button class="show-hea-hitter angle_btn active-scale">Show Heavy Hitter</button>
                    <button class="remove-hea-hitter angle_btn">Remove Heavy Hitter</button>
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
                    <div class="pull-right top10-chart-btn light-orange">    
					<button id="b8" class="angle_btn active-scale">Show Heavy Hitter</button>                
                    <button id="b7" class="angle_btn">Remove Heavy Hitter</button>
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
      
      <div class="row"><div class="col-lg-12"><a href="javascript:void(0)" class="top-table-btn-link"><span><i class="top-table-icon"></i></span>Top 10 Table</a></div>
    </div>
    </div>
    
    <div class="row">
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
  </div>