<%@ page import="twitter_manager_webserver.dto.CampaignDTO" %>

<div class="fieldcontain ${hasErrors(bean: campaignInstance, field: 'name', 'error')} required">
    <label for="name">
        <g:message code="campaign.name.label" default="Name" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="name" required="" value="${campaignInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: campaignInstance, field: 'keywords', 'error')} required">
    <label for="keywords">
        <g:message code="campaign.keywords.label" default="Keywords" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="keywords" required="" value="${campaignInstance?.keywords}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: campaignInstance, field: 'andRule', 'error')} required">
	<label for="andRule">
		<g:message code="campaign.andRule.label" default="And Rule" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="andRule" required="" value="${campaignInstance?.andRule}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: campaignInstance, field: 'turnedOn', 'error')} ">
	<label for="turnedOn">
		<g:message code="campaign.turnedOn.label" default="Turned On" />
		
	</label>
	<g:checkBox name="turnedOn" value="${campaignInstance?.turnedOn}" />

</div>

