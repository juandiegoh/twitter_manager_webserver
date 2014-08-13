package twitter_manager_webserver.dto

class CampaignDTO {
    Long id
    String name
    String keywords
    Boolean turnedOn
    String andRule

    boolean hasErrors() {
        name == null || keywords == null || turnedOn == null || andRule == null
    }
}
