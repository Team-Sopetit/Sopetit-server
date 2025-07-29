package com.soptie.server.persistence.support;

public class QueryConstants {

	/**
	 * Member Routine
	 */
	public static final String DELETE_FORCE_MEMBER_ROUTINE = "DELETE FROM member_routine WHERE id = :id";

	public static final String FIND_DELETED_MEMBER_ROUTINES_IN_TARGETS
		= "SELECT * FROM member_routine "
		+ "WHERE member_id = :memberId AND is_deleted = true AND routine_id IN (:targetIds)";

	public static final String FORCE_DELETE_MEMBER_ROUTINES = "DELETE FROM member_routine WHERE member_id = :memberId";
}
