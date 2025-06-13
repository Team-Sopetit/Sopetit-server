package com.soptie.server.api.controller.member;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.generic.dto.SuccessResponse;
import com.soptie.server.api.controller.member.docs.MemberVisitApiDocs;

@RestController
@RequestMapping("/api/v1/member/visit")
public class MemberVisitApi implements MemberVisitApiDocs {
	@Override
	public SuccessResponse<?> visit(Principal principal) {
		return null;
	}
}
