package com.academy.lecture.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.mapper.TeacherMapper;

/**
 * Teacher Service
 * 강사 관리 서비스 (직접 구현)
 */
@Service(value="teacherservice")
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * Kind 리스트 조회
     */
    public List<HashMap<String, String>> getKindList(TeacherVO teacherVO){
        return teacherMapper.getKindList(teacherVO);
    }

    /**
     * Subject 리스트 조회
     */
    public List<HashMap<String, String>> getSubjectList(TeacherVO teacherVO){
        return teacherMapper.getSubjectList(teacherVO);
    }

    /**
     * Teacher 목록 조회
     */
    public List<HashMap<String, String>> teacherList(TeacherVO teacherVO){
        return teacherMapper.teacherList(teacherVO);
    }

    /**
     * Teacher 목록 개수 조회
     */
    public int teacherListCount(TeacherVO teacherVO){
        return teacherMapper.teacherListCount(teacherVO);
    }

    /**
     * Teacher 전체 목록 조회
     */
    public List<HashMap<String, String>> teacherAllList(TeacherVO teacherVO){
        return teacherMapper.teacherAllList(teacherVO);
    }

    /**
     * Teacher 전체 목록 개수 조회
     */
    public int teacherAllListCount(TeacherVO teacherVO) {
        return teacherMapper.teacherAllListCount(teacherVO);
    }

    /**
     * Teacher ID 중복 체크
     */
    public int teacherIdCheck(TeacherVO teacherVO){
        return teacherMapper.teacherIdCheck(teacherVO);
    }

    /**
     * Teacher 등록 (배열 처리 포함)
     */
    @SuppressWarnings("unchecked")
    public void teacherInsert(Object obj){

        teacherMapper.teacherInsert(obj);

        String[] ORI_CATEGORY_CODE = (String[])((HashMap<String, Object>)obj).get("ORI_CATEGORY_CODE");
        String[] CETCARR = (String[])((HashMap<String, Object>)obj).get("CETCARR");

        if(ORI_CATEGORY_CODE == null) { // 등록
            for(int i=0; i<CETCARR.length; i++){
                ((HashMap<String, String>)obj).put("CATEGORY_CODE", CETCARR[i]);
                teacherMapper.teacherCategoryInsert(obj);
            }
        } else {    // 수정
            for(int i=0; i<ORI_CATEGORY_CODE.length;i++){
                boolean isDel = true;
                for(int j=0; j<CETCARR.length; j++){
                    if(ORI_CATEGORY_CODE[i].equals(CETCARR[j])) {
                        isDel = false;
                    }
                }
                if(isDel) {
                    ((HashMap<String, String>)obj).put("DEL_CATEGORY_CODE", ORI_CATEGORY_CODE[i]);
                    teacherMapper.teacherCategoryDelete(obj);
                }
            }

            for(int i=0; i<CETCARR.length; i++){
                boolean isIns = true;
                for(int j=0; j<ORI_CATEGORY_CODE.length;j++){
                    if(CETCARR[i].equals(ORI_CATEGORY_CODE[j])) {
                        isIns = false;
                    }
                }
                if(isIns) {
                    ((HashMap<String, String>)obj).put("CATEGORY_CODE", CETCARR[i]);
                    teacherMapper.teacherCategoryInsert(obj);
                }
            }
        }

        teacherMapper.teacherSubjectDelete(obj);

        String[] SETCARR = (String[])((HashMap<String, Object>)obj).get("SETCARR");
        if(SETCARR != null){
            for(int i=0; i<SETCARR.length; i++){
                ((HashMap<String, String>)obj).put("SUBJECT_CD", SETCARR[i]);
                int teacherMain_Category_Subject = teacherMapper.teacherMain_Category_Subject(obj);
                int teacherIntro_Category_Subject = teacherMapper.teacherIntro_Category_Subject(obj);
                teacherMapper.teacherSubjectInsert(obj);

                if(ORI_CATEGORY_CODE == null) {
                    teacherMapper.teacherMain_Category_Insert(obj);
                    teacherMapper.teacherIntro_Category_Insert(obj);
                    teacherMapper.teacherIntro_F_Category_Insert(obj);
                }else{

                    if(teacherMain_Category_Subject == 0){

                        teacherMapper.teacherMain_Category_Insert(obj);
                    }else{

                    }
                    if(teacherIntro_Category_Subject == 0){

                        teacherMapper.teacherIntro_Category_Insert(obj);
                        teacherMapper.teacherIntro_F_Category_Insert(obj);
                    }else{

                    }
                }
            }
        }
    }

    /**
     * Teacher 상세 조회
     */
    public List<HashMap<String, String>> teacherView(TeacherVO teacherVO){
        return teacherMapper.teacherView(teacherVO);
    }

    /**
     * Teacher 수정 (배열 처리 포함)
     */
    @SuppressWarnings("unchecked")
    public void teacherUpdate(Object obj){
        teacherMapper.teacherUpdate(obj);

        String[] ORI_CATEGORY_CODE = (String[])((HashMap<String, Object>)obj).get("ORI_CATEGORY_CODE");
        String[] CETCARR = (String[])((HashMap<String, Object>)obj).get("CETCARR");

        if(ORI_CATEGORY_CODE == null) { // 등록
            for(int i=0; i<CETCARR.length; i++){
                ((HashMap<String, String>)obj).put("CATEGORY_CODE", CETCARR[i]);
                teacherMapper.teacherCategoryInsert(obj);
            }
        } else {    // 수정
            for(int i=0; i<ORI_CATEGORY_CODE.length;i++){
                boolean isDel = true;
                for(int j=0; j<CETCARR.length; j++){
                    if(ORI_CATEGORY_CODE[i].equals(CETCARR[j])) {
                        isDel = false;
                    }
                }
                if(isDel) {
                    ((HashMap<String, String>)obj).put("DEL_CATEGORY_CODE", ORI_CATEGORY_CODE[i]);
                    teacherMapper.teacherCategoryDelete(obj);
                }
            }

            for(int i=0; i<CETCARR.length; i++){
                boolean isIns = true;
                for(int j=0; j<ORI_CATEGORY_CODE.length;j++){
                    if(CETCARR[i].equals(ORI_CATEGORY_CODE[j])) {
                        isIns = false;
                    }
                }
                if(isIns) {
                    ((HashMap<String, String>)obj).put("CATEGORY_CODE", CETCARR[i]);
                    teacherMapper.teacherCategoryInsert(obj);
                }
            }
        }

        teacherMapper.teacherSubjectDelete(obj);
        teacherMapper.teacherMain_Category_Delete(obj);
        teacherMapper.teacherIntro_Category_Delete(obj);
        teacherMapper.teacherIntro_F_Category_Delete(obj);

        String[] SETCARR = (String[])((HashMap<String, Object>)obj).get("SETCARR");
        String[] OFF_SETCARR = (String[])((HashMap<String, Object>)obj).get("OFF_SETCARR");
        String ON_SBJ = "";
        if(SETCARR != null && SETCARR.length > 0){
            for(int i=0; i<SETCARR.length; i++){
                ((HashMap<String, String>)obj).put("SUBJECT_CD", SETCARR[i]);
                ((HashMap<String, String>)obj).put("USE_ON", "Y");
                ((HashMap<String, String>)obj).put("USE_OFF", "N");
                teacherMapper.teacherSubjectInsert(obj);

                if(ORI_CATEGORY_CODE == null) {
                    teacherMapper.teacherMain_Category_Insert(obj);
                    teacherMapper.teacherIntro_Category_Insert(obj);
                    teacherMapper.teacherIntro_F_Category_Insert(obj);
                }else{
                    int teacherMain_Category_Subject = teacherMapper.teacherMain_Category_Subject(obj);
                    int teacherIntro_Category_Subject = teacherMapper.teacherIntro_Category_Subject(obj);
                    int teacherIntro_F_Category_Subject = teacherMapper.teacherIntro_F_Category_Subject(obj);
                    if(teacherMain_Category_Subject == 0){

                        teacherMapper.teacherMain_Category_Insert(obj);

                    }
                    if(teacherIntro_Category_Subject == 0){
                        teacherMapper.teacherIntro_Category_Insert(obj);
                    }
                    if(teacherIntro_F_Category_Subject == 0){
                        teacherMapper.teacherIntro_F_Category_Insert(obj);
                    }

                }
                ON_SBJ += SETCARR[i]+",";
          }
       }
        ((HashMap<String, String>)obj).put("USE_ON", "");
        ((HashMap<String, String>)obj).put("USE_OFF", "");

        if(OFF_SETCARR != null && OFF_SETCARR.length > 0){
            for(int j=0; j<OFF_SETCARR.length; j++){
                if(ON_SBJ.contains(OFF_SETCARR[j])){
                    ((HashMap<String, String>)obj).put("USE_OFF", "Y");
                    teacherMapper.teacherSubjectUpdate(obj);
                }else{
                    ((HashMap<String, String>)obj).put("SUBJECT_CD", OFF_SETCARR[j]);
                    ((HashMap<String, String>)obj).put("USE_ON", "N");
                    ((HashMap<String, String>)obj).put("USE_OFF", "Y");
                    int sbjCnt = teacherMapper.teacherSubjectCount(obj);
                    if(sbjCnt==0){
                        teacherMapper.teacherSubjectInsert(obj);

                        if(ORI_CATEGORY_CODE == null && (SETCARR == null || SETCARR.length < 1)) {
                            teacherMapper.teacherMain_Category_Insert(obj);
                            teacherMapper.teacherIntro_Category_Insert(obj);
                            teacherMapper.teacherIntro_F_Category_Insert(obj);
                        }else{
                            int teacherMain_Category_Subject = teacherMapper.teacherMain_Category_Subject(obj);
                            int teacherIntro_Category_Subject = teacherMapper.teacherIntro_Category_Subject(obj);
                            int teacherIntro_F_Category_Subject = teacherMapper.teacherIntro_F_Category_Subject(obj);
                            if(teacherMain_Category_Subject == 0){

                                teacherMapper.teacherMain_Category_Insert(obj);

                            }
                            if(teacherIntro_Category_Subject == 0){
                                teacherMapper.teacherIntro_Category_Insert(obj);
                            }
                            if(teacherIntro_F_Category_Subject == 0){
                                teacherMapper.teacherIntro_F_Category_Insert(obj);
                            }
                        }
                    }
                }
                ((HashMap<String, String>)obj).put("USE_ON", "");
                ((HashMap<String, String>)obj).put("USE_OFF", "");
            }
        }
    }

    /**
     * Teacher Category 등록
     */
    public void teacherCategoryInsert(TeacherVO teacherVO){
        teacherMapper.teacherCategoryInsert(teacherVO);
    }

    /**
     * Teacher Subject 등록
     */
    public void teacherSubjectInsert(TeacherVO teacherVO){
        teacherMapper.teacherSubjectInsert(teacherVO);
    }

    /**
     * Teacher 사용 여부 수정 (배열 처리 포함)
     */
    @SuppressWarnings("unchecked")
    public void teacherIsUseUpdate(Object obj){
        String[] DEL_ARR = (String[])((HashMap<String, Object>)obj).get("DEL_ARR");
        if(DEL_ARR != null && DEL_ARR.length > 0){
            for(int i=0; i<DEL_ARR.length; i++){
                HashMap<String, String> params = new  HashMap<String, String>();
                params.put("USER_ID", DEL_ARR[i]);

                teacherMapper.teacherIsUseUpdate(params);
            }
        }
    }

    /**
     * Teacher 삭제
     */
    public void teacherDelete(TeacherVO teacherVO){
        teacherMapper.teacherDelete(teacherVO);
    }

    /**
     * Teacher Category 삭제
     */
    public void teacherCategoryDelete(TeacherVO teacherVO){
        teacherMapper.teacherCategoryDelete(teacherVO);
    }

    /**
     * Teacher Subject 삭제
     */
    public void teacherSubjectDelete(TeacherVO teacherVO){
        teacherMapper.teacherSubjectDelete(teacherVO);
    }

    /**
     * Teacher 순서 수정 (배열 처리 포함)
     */
    @SuppressWarnings("unchecked")
    public void teacherSeqUpdate(Object obj){
        String[] NUM = (String[])((HashMap<String, Object>)obj).get("NUM");
        String[] SEQARR = (String[])((HashMap<String, Object>)obj).get("SEQ");
        String[] PROFESSOR_USER_IDARR = (String[])((HashMap<String, Object>)obj).get("USER_ID");
        String[] SUBJECT_CD = (String[])((HashMap<String, Object>)obj).get("SUBJECT_CD");

        if(SEQARR != null && SEQARR.length > 0){
            for(int i=0; i<SEQARR.length; i++){
                HashMap<String, String> params = new  HashMap<String, String>();
                params.put("NUM", NUM[i]);
                params.put("SEQ", SEQARR[i]);
                params.put("USER_ID", PROFESSOR_USER_IDARR[i]);
                params.put("SUBJECT_CD", SUBJECT_CD[i]);
                params.put("ONOFFDIV", ((HashMap<String, Object>)obj).get("ONOFFDIV").toString());
                params.put("SEARCHCATEGORY", ((HashMap<String, Object>)obj).get("SEARCHCATEGORY").toString());

                teacherMapper.teacherSeqUpdate(params);
            }
        }
    }

    /**
     * Teacher Book Log 조회
     */
    public List<HashMap<String, String>> teacherBookLog(TeacherVO teacherVO){
        return teacherMapper.teacherBookLog(teacherVO);
    }

    /**
     * Teacher Main Category 등록
     */
    public void teacherMain_Category_Insert(TeacherVO teacherVO){
        teacherMapper.teacherMain_Category_Insert(teacherVO);
    }

    /**
     * Teacher Main Category 삭제
     */
    public void teacherMain_Category_Delete(TeacherVO teacherVO){
        teacherMapper.teacherMain_Category_Delete(teacherVO);
    }

    /**
     * Teacher Intro Category 등록
     */
    public void teacherIntro_Category_Insert(TeacherVO teacherVO){
        teacherMapper.teacherIntro_Category_Insert(teacherVO);
    }

    /**
     * Teacher Intro F Category 등록
     */
    public void teacherIntro_F_Category_Insert(TeacherVO teacherVO){
        teacherMapper.teacherIntro_F_Category_Insert(teacherVO);
    }

    /**
     * Teacher Intro Category 삭제
     */
    public void teacherIntro_Category_Delete(TeacherVO teacherVO){
        teacherMapper.teacherIntro_Category_Delete(teacherVO);
    }

    /**
     * Teacher Intro F Category 삭제
     */
    public void teacherIntro_F_Category_Delete(TeacherVO teacherVO){
        teacherMapper.teacherIntro_F_Category_Delete(teacherVO);
    }

    /**
     * Teacher Main 리스트 조회
     */
    public List<HashMap<String, String>> teacherMainList(TeacherVO teacherVO){
        return teacherMapper.teacherMainList(teacherVO);
    }

    /**
     * Teacher Main 리스트 개수 조회
     */
    public int teacherMainListCount(TeacherVO teacherVO){
        return teacherMapper.teacherMainListCount(teacherVO);
    }

    /**
     * Teacher 검색 리스트 조회
     */
    public List<HashMap<String, String>> findTeacherList(TeacherVO teacherVO){
        return teacherMapper.findTeacherList(teacherVO);
    }

    /**
     * Teacher Main Category Subject 조회
     */
    public int teacherMain_Category_Subject(TeacherVO teacherVO){
        return teacherMapper.teacherMain_Category_Subject(teacherVO);
    }

    /**
     * Teacher Intro Category Subject 조회
     */
    public int teacherIntro_Category_Subject(TeacherVO teacherVO){
        return teacherMapper.teacherIntro_Category_Subject(teacherVO);
    }

    /**
     * Teacher Intro F Category Subject 조회
     */
    public int teacherIntro_F_Category_Subject(TeacherVO teacherVO){
        return teacherMapper.teacherIntro_F_Category_Subject(teacherVO);
    }

    /**
     * Teacher Intro 리스트 조회
     */
    public List<HashMap<String, String>> teacherIntroList(TeacherVO teacherVO){
        return teacherMapper.teacherIntroList(teacherVO);
    }

    /**
     * Teacher Intro 리스트 개수 조회
     */
    public int teacherIntroListCount(TeacherVO teacherVO){
        return teacherMapper.teacherIntroListCount(teacherVO);
    }

    /**
     * Teacher Intro Off 리스트 조회
     */
    public List<HashMap<String, String>> teacherIntro_offList(TeacherVO teacherVO){
        return teacherMapper.teacherIntro_offList(teacherVO);
    }

    /**
     * Teacher Intro Off 리스트 개수 조회
     */
    public int teacherIntro_offListCount(TeacherVO teacherVO){
        return teacherMapper.teacherIntro_offListCount(teacherVO);
    }
}