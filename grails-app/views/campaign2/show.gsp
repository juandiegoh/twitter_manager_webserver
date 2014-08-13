
<%@ page import="twitter_manager_webserver.Campaign" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'campaign.label', default: 'Campaign')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-campaign" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-campaign" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list campaign">
			
				<g:if test="${campaignInstance?.andRule}">
				<li class="fieldcontain">
					<span id="andRule-label" class="property-label"><g:message code="campaign.andRule.label" default="And Rule" /></span>
					
						<span class="property-value" aria-labelledby="andRule-label"><g:fieldValue bean="${campaignInstance}" field="andRule"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${campaignInstance?.keywords}">
				<li class="fieldcontain">
					<span id="keywords-label" class="property-label"><g:message code="campaign.keywords.label" default="Keywords" /></span>
					
						<span class="property-value" aria-labelledby="keywords-label"><g:fieldValue bean="${campaignInstance}" field="keywords"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${campaignInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="campaign.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${campaignInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${campaignInstance?.turnedOn}">
				<li class="fieldcontain">
					<span id="turnedOn-label" class="property-label"><g:message code="campaign.turnedOn.label" default="Turned On" /></span>
					
						<span class="property-value" aria-labelledby="turnedOn-label"><g:formatBoolean boolean="${campaignInstance?.turnedOn}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:campaignInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${campaignInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
