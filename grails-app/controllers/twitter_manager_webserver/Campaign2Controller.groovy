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

        def hours = result.index
        def countries = result.country
        def followers = result.followers

        def hoursString = getHoursString(hours.hours)

        def dateFromResult = DateParseUtil.getStringFromDate(dateFrom)
        def dateToResult = DateParseUtil.getStringFromDate(dateTo)

        def countriesString = getCountriesString(countries.countries)
        def categoriesString = getFollowersString(followers.categories)

        [campaignName: "${params.id}", dateFrom: "${dateFromResult}", dateTo: "${dateToResult}",
                hours: hoursString, positiveLabel: "POSITIVE",
                positiveValues: hours.positives, negativeLabel: "NEGATIVE",
                negativeValues: hours.negatives,
                countriesPositive:countries.positives,
                countriesNegative:countries.negatives,
                countriesNeutral:countries.neutrals,
                countries: countriesString,
                followersCategories: categoriesString,
                followersPositives: followers.positives,
                followersNegatives: followers.negatives
        ]
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

    def getFollowersString(followers) {
        def joins = followers.collect { "\"${it}\"" }.join(",")
        return "[ ${joins} ]"
    }

    def getCountriesString(countries) {
        def joins = countries.collect { "\"${it}\"" }.join(",")
        return "[ ${joins} ]"
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
            respond campaignInstance.errors, view:'create'
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

    def turnon() {
        def result = this.campaignService.turnOn(params.id)
        def message = result ? "Campaign ${params.id} was turned on!" : "Campaign ${params.id} could not be turned on beacause of an error!"

        flash.message = message
        redirect action: "index", method: "GET"
    }

    def turnoff() {
        def result = this.campaignService.turnOff(params.id)
        def message = result ? "Campaign ${params.id} was turned off!" : "Campaign ${params.id} could not be turned off beacause of an error!"

        flash.message = message
        redirect action: "index", method: "GET"
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
