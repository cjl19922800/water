package com.shanghaiwater.mcs.admin.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shanghaiwater.mcs.common.vo.UserRelationship;

@FeignClient(name = "relationship", url = "http://localhost:8020")
//@FeignClient(name = "relationship", url = "http://192.168.6.71:8020")
public interface RelationService {

	@RequestMapping(value ="/selectByUserId")
	public List<UserRelationship> selectByUserId(@RequestParam("userId") String userId);
	
	@RequestMapping(value ="/saveUserRelationshipByCardId")
	public Boolean saveUserRelationshipByCardId(@RequestParam("userId") String userId,
			@RequestParam("cardId") String cardId,@RequestParam("relationship") String relationship);
	
	@RequestMapping(value="/saveUserRelationshipByAcctId")
	public Boolean saveUserRelationshipByAcctId(@RequestParam("userId") String userId,
			@RequestParam("acctId") String acctId,@RequestParam("relationship") String relationship);

	@RequestMapping(value="/updateUserRelationshipByCardId")
	public Boolean updateUserRelationshipByCardId(@RequestParam("cardId") String cardId,
			@RequestParam("relationship") String relationship);

	@RequestMapping(value="/updateUserRelationshipByAcctId")
	public Boolean updateUserRelationshipByAcctId(@RequestParam("acctId") String acctId,
			@RequestParam("relationship") String relationship);
}
