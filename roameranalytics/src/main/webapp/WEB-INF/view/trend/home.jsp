<div ng-if="isTrendTab()" >
	<trend-header/>
	<heat-map ng-if="isHeatMapSelected()" />
	<top-ten ng-if="isTopTenSelected()" />
	<roaming-trends ng-if="isRoamingTrendsSelected()" />
</div>
<div ng-if="isMicroSegmentTab()">
	Microsegment Body
</div>
<div ng-if="isPredictTab()">
	Predict Body
</div>