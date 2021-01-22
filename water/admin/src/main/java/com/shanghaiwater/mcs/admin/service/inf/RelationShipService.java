package com.shanghaiwater.mcs.admin.service.inf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shanghaiwater.mcs.admin.feign.RelationService;
import com.shanghaiwater.mcs.common.vo.UserRelationship;

@Service
public class RelationShipService {

	@Autowired
	private RelationService service;

	public List<UserRelationship> selectByUserId(String userId) {
		return service.selectByUserId(userId);
	}

	public Boolean updateUserRelationshipByCardId(String cardId, String relationship) {
		return service.updateUserRelationshipByCardId(cardId, relationship);
	}

	public Boolean updateUserRelationshipByAcctId(String acctId, String relationship) {
		return service.updateUserRelationshipByAcctId(acctId, relationship);
	}

	public Boolean saveUserRelationshipByCardId(String userId, String cardId, String relationship) {
		return service.saveUserRelationshipByCardId(userId, cardId, relationship);
	}

	public Boolean saveUserRelationshipByAcctId(String userId, String acctId, String relationship) {
		return service.saveUserRelationshipByAcctId(userId, acctId, relationship);
	}

}
