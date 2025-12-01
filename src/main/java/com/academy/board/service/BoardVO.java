package com.academy.board.service;

import java.io.Serializable;

import com.academy.common.CommonVO;

/**
 *
 * 게시판 VO 클래스
 * @author rainend
 * @version 1.0
 * @see
 ** <pre>
 * << 개정이력(Modification Information) >>
 *
 *   	  수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.11		 rainend		 게시판 등록
 * </pre>
 */
public class BoardVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = 638950577710720796L;

	/* Identification */
	/** 게시판관리SEQ (BOARD_MNG_SEQ) */
	private String boardMngSeq;
	/** 게시판SEQ (BOARD_SEQ) */
	private String boardSeq;
	/** 부모 게시글SEQ (PARENT_BOARD_SEQ) */
	private String parentBoardSeq;

	/* Content */
	/** 제목 (SUBJECT) - underlying field is boardTitle */
	private String subject;
	/** 내용 (CONTENT) - underlying field is boardMemo */
	private String boardMemo;
	/** 답변(ANSWER) - BLOB */
	private byte[] answer;

	/* Metadata / flags */
	private String openYn; // OPEN_YN
	private String noticeTopYn; // NOTICE_TOP_YN
	// isUse inherited from CommonVO
	private String recommend; // RECOMMEND
	private Long hits; // HITS

	/* Files */
	private String filePath;
	private String fileName;
	private String realFileName;
	private String thumbnailFilePath;
	private String thumbnailFileName;
	private String thumbnailFileRealName;

	/* Audit - inherited from CommonVO: regDt, regId, updDt, updId */
	private String createName; // CREATENAME

	/* Other */
	private Long boardSeq3;
	private String examType;
	private String examArea;
	private String examCate;
	private String examSub;
	private String categoryCode;
	private String profId;
	private String boardType;
	private String mockcode;
	private String diviceType;

	/* ---------------------------------- */
	/* Aliases / compatibility methods */
	/** boardId alias (used by mapper as parameter) */
	public String getBoardId() { return this.boardSeq; }
	public void setBoardId(String boardId) { this.boardSeq = boardId; }

	/** subject alias for SUBJECT column (maps to boardTitle) */
	public String getSubject() { return this.subject; }
	public void setSubject(String subject) { this.subject = subject; }

	/** content alias for CONTENT column (maps to boardMemo) */
	public String getContent() { return this.boardMemo; }
	public void setContent(String content) { this.boardMemo = content; }

	/* Standard getters/setters (boardSeq/boardMngSeq and JVM bean style) */
	public String getBoardMngSeq() { return boardMngSeq; }
	public void setBoardMngSeq(String boardMngSeq) { this.boardMngSeq = boardMngSeq; }

	public String getBoardSeq() { return boardSeq; }
	public void setBoardSeq(String boardSeq) { this.boardSeq = boardSeq; }

	public String getParentBoardSeq() { return parentBoardSeq; }
	public void setParentBoardSeq(String parentBoardSeq) { this.parentBoardSeq = parentBoardSeq; }

	public String getBoardMemo() { return boardMemo; }
	public void setBoardMemo(String boardMemo) { this.boardMemo = boardMemo; }

	public byte[] getAnswer() { return answer; }
	public void setAnswer(byte[] answer) { this.answer = answer; }

	public String getOpenYn() { return openYn; }
	public void setOpenYn(String openYn) { this.openYn = openYn; }

	public String getNoticeTopYn() { return noticeTopYn; }
	public void setNoticeTopYn(String noticeTopYn) { this.noticeTopYn = noticeTopYn; }

	// isUse getter/setter inherited from CommonVO

	public String getRecommend() { return recommend; }
	public void setRecommend(String recommend) { this.recommend = recommend; }

	public Long getHits() { return hits; }
	public void setHits(Long hits) { this.hits = hits; }

	public String getFilePath() { return filePath; }
	public void setFilePath(String filePath) { this.filePath = filePath; }

	public String getFileName() { return fileName; }
	public void setFileName(String fileName) { this.fileName = fileName; }

	public String getRealFileName() { return realFileName; }
	public void setRealFileName(String realFileName) { this.realFileName = realFileName; }

	public String getThumbnailFilePath() { return thumbnailFilePath; }
	public void setThumbnailFilePath(String thumbnailFilePath) { this.thumbnailFilePath = thumbnailFilePath; }

	public String getThumbnailFileName() { return thumbnailFileName; }
	public void setThumbnailFileName(String thumbnailFileName) { this.thumbnailFileName = thumbnailFileName; }

	public String getThumbnailFileRealName() { return thumbnailFileRealName; }
	public void setThumbnailFileRealName(String thumbnailFileRealName) { this.thumbnailFileRealName = thumbnailFileRealName; }

	// regDt, regId, updDt, updId getter/setter inherited from CommonVO

	public String getCreateName() { return createName; }
	public void setCreateName(String createName) { this.createName = createName; }

	public Long getBoardSeq3() { return boardSeq3; }
	public void setBoardSeq3(Long boardSeq3) { this.boardSeq3 = boardSeq3; }

	public String getExamType() { return examType; }
	public void setExamType(String examType) { this.examType = examType; }

	public String getExamArea() { return examArea; }
	public void setExamArea(String examArea) { this.examArea = examArea; }

	public String getExamCate() { return examCate; }
	public void setExamCate(String examCate) { this.examCate = examCate; }

	public String getExamSub() { return examSub; }
	public void setExamSub(String examSub) { this.examSub = examSub; }

	public String getCategoryCode() { return categoryCode; }
	public void setCategoryCode(String categoryCode) { this.categoryCode = categoryCode; }

	public String getProfId() { return profId; }
	public void setProfId(String profId) { this.profId = profId; }

	public String getBoardType() { return boardType; }
	public void setBoardType(String boardType) { this.boardType = boardType; }

	public String getMockcode() { return mockcode; }
	public void setMockcode(String mockcode) { this.mockcode = mockcode; }

	public String getDiviceType() { return diviceType; }
	public void setDiviceType(String diviceType) { this.diviceType = diviceType; }

}