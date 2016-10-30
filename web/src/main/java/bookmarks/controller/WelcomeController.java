/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package bookmarks.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;


@Controller
public class WelcomeController {

	@Value("${application.message}")
	private String message = "Hello World";

	@RequestMapping(value = {"/","/home","/welcome"}, method = RequestMethod.GET)
	public String index() {
		return "redirect:/index";
	}

	@RequestMapping(value = "/datepicker")
	public String datepicker(){
		return "datepicker";
	}

	@RequestMapping(value = "/blank")
	public String blank(){
		return "blank";
	}


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Map<String, Object> model, String error ) {
		model.put("error",error);
		return "pages-login";
	}

	@RequestMapping(value = "/500")
	public String error500() {
		return "pages-error-500";
	}

	@RequestMapping(value = "/404")
	public String error404() {
		return "pages-error-404";
	}

	@RequestMapping(value = "/403")
	public String error403() {
		return "404";
	}

	@RequestMapping("/foo")
	public String foo(Map<String, Object> model) {
		throw new RuntimeException("Foo");
	}



}
