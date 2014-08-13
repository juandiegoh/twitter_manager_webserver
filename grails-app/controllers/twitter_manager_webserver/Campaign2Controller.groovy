package twitter_manager_webserver

import groovy.time.TimeCategory
import twitter_manager_webserver.dto.CampaignDTO
import twitter_manager_webserver.utils.DateParseUtil

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND

class Campaign2Controller {

    CampaignService campaignService

    def index(Integer max) {
        def allCampaigns = this.campaignService.findAll()
        [allCampaigns: allCampaigns, campaignInstanceCount: allCampaigns.size()]
    }

    def report() {
        def dateFrom = getDateFrom()
        def dateTo = getDateTo()

        def result = this.campaignService.getReportsDataFromTweetsOfCampaign(params.id, dateFrom, dateTo)
        def hoursString = getHoursString(result.hours)

        def dateFromResult = DateParseUtil.getStringFromDate(dateFrom)
        def dateToResult = DateParseUtil.getStringFromDate(dateTo)

        [campaignName: "${params.id}", dateFrom: "${dateFromResult}", dateTo: "${dateToResult}",
                hours: hoursString, positiveLabel: "POSITIVE",
                positiveValues: result.positives, negativeLabel: "NEGATIVE",
                negativeValues: result.negatives]
    }

    def getDateTo() {
        if (params.date_to) {
            return DateParseUtil.createDateFrom(params.date_to)
        } else {
            return new Date()
        }
    }

    def getDateFrom() {
        if (params.date_from) {
            return DateParseUtil.createDateFrom(params.date_from)
        } else {
            use(TimeCategory) {
                return new Date() - 6.hours
            }
        }
    }

    def getHoursString(hours) {
        def joins = hours.collect { "\"day ${it} hs\"" }.join(",")
        return "[ ${joins} ]"
    }

    def create() {
        respond new CampaignDTO(turnedOn: true)
    }

    def save(CampaignDTO campaignInstance) {
        if (campaignInstance == null) {
            notFound()
            return
        }

        if (campaignInstance.hasErrors()) {
            respond [:], view:'create'
            return
        }

        this.campaignService.save(campaignInstance)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'campaign.label', default: 'Campaign'), campaignInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { respond campaignInstance, [status: CREATED] }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'campaign.label', default: 'Campaign'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
