package com.koron.web.workflowUtils;


public interface StatusMapper {


    /**
     * 停用状态 : 0
     */
    public static final Integer STATUS_COMMON_CLOSE = 0;
    /**
     * 启用状态（审核已通过）: 1
     */
    public static final Integer STATUS_COMMON_OPEN = 1;
    /**
     * 草稿状态: 2
     */
    public static final Integer STATUS_COMMON_DRAFT = 2;
    /**
     * 已提交状态（待审核状态）: 3
     */
    public static final Integer STATUS_COMMON_SUBMIT = 3;
    /**
     * 已驳回状态: 4 (操作是1)
     */
    public static final Integer STATUS_COMMON_REBUT = 4;

    /**
     * 废弃状态 : 5
     */
    public static final Integer STATUS_COMMON_LOST = 5;

    /**
     * 转办状态 : 6
     */
    public static final Integer STATUS_COMMON_TURN = 6;

    /**
     * 结束状态 : 7
     */
    public static final Integer STATUS_COMMON_END = 7;


}
