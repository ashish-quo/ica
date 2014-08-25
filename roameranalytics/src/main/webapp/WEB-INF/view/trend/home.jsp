<div ng-if="isTrendTab()" ng-controller="TrendController">
	<div>
		<trend-header />
	</div>
	<div ng-if="isHeatMapSelected()">Heat Map</div>
	<div ng-if="isTopTenSelected()" >Top Ten</div>
	<div ng-if="isRoamingTrendSelected()"><roaming-trend /></div>
</div>
<div ng-if="isMicroSegmentTab()">Microsegment Body</div>
<div ng-if="isPredictTab()">Predict Body</div>