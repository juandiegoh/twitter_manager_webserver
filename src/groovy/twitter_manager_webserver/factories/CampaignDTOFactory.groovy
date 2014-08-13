package twitter_manager_webserver.factories

import twitter_manager_webserver.dto.CampaignDTO

class CampaignDTOFactory {

    def createListOfCampaignDTOFromJSONList(jsonList) {
        return jsonList.collect { jsonCampaign ->
            return createCampaignDTOFromJson(jsonCampaign)
        }
    }

    def createCampaignDTOFromJson(jsonCampaign) {
        CampaignDTO campaignDTO = new CampaignDTO()
        campaignDTO.with {
            it.id = jsonCampaign.id
            it.name = jsonCampaign.name
            it.keywords = jsonCampaign.keywords
            it.turnedOn = jsonCampaign.turned_on
            it.andRule = getAndRuleFromRules(jsonCampaign.rules)
        }
        return campaignDTO
    }

    def getAndRuleFromRules(rules) {
        def andRule = rules.find {
            it.type = 'and_rule'
        }
        return andRule?.and_words
    }

    def createJSONFromCampaign(CampaignDTO campaignDTO) {
        return [
                and_keywords : campaignDTO.andRule,
                keywords : campaignDTO.keywords,
                name : campaignDTO.name,
                turned_on : campaignDTO.turnedOn
        ]
    }
}
