package twitter_manager_webserver.repositories

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import twitter_manager_webserver.factories.CampaignDTOFactory
import twitter_manager_webserver.utils.RetryUtil

class RestCampaignRepository implements CampaignRepository {

    CampaignDTOFactory campaignDTOFactory

    private static final String API_URL = "http://localhost:8080/twitter_manager_consumer/"

    @Override
    def findAll() {
        def response = null
        try {
            HTTPBuilder httpBuilder = new HTTPBuilder(API_URL)
            response = RetryUtil.retry(3, 100) {
                return httpBuilder.get(
                        path: 'campaigns',
                        contentType: ContentType.JSON)
            }
        } catch (e) {
            log.error "Hubo un error al leer de la api de campaigns", e
            throw e
        }
        return campaignDTOFactory.createListOfCampaignDTOFromJSONList(response)
    }
}
