package kr.hs.emirim.nmb2017.springmvc4.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {
	
	@Autowired private Twitter twitter;
	
	@RequestMapping("/")
	public String search() {
		return "searchPage";
	}
	
	@RequestMapping("/result")
	public String hello(@RequestParam(defaultValue="티나") String search, Model model) {
		SearchResults searchResults = twitter.searchOperations().search(search); //전체 트위터 검색 결과
		
		//List<Tweet> ts=searchResults.getTweets();
		//ArrayList<String> tss=new ArrayList<>();
		//for(Tweet t: ts) {
		//	tss.add(t.getText());
		//} 원래 이 코드를 해야하는데 밑에 한줄로 한것!! 람다식
		
		List<Tweet> tweets = searchResults.getTweets(); 
		model.addAttribute("tweets", tweets);
		model.addAttribute("search", search);
		return "tweets";
		
	}
}
