package twitter_manager_webserver.dto

import grails.validation.Validateable

@Validateable
class CampaignDTO {
    Long id
    String name
    String keywords
    Boolean turnedOn
    String andRule

    static constraints = {
        name blank: false
        keywords blank: false
        turnedOn blank: false
        turnedOn blank: false
    }
}
