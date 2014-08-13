class UrlMappings {

	static mappings = {

//        "/campaign2"(controller: "campaign2", parseRequest: true){
//            action = [GET: 'index']
//        }
//
//        "/campaign2/report/$id"(controller: "campaign2", parseRequest: true){
//            action = [GET: 'report']
//        }
//
//        "/campaign2/create"(controller: "campaign2", parseRequest: true){
//            action = [GET: 'create']
//        }

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
