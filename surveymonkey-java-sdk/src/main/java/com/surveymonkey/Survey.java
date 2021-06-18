package com.surveymonkey;

public class Survey {

  /**
   * This is a domain object to be used for DTO or mapping JSON deserialized object.
   */

    //Example input
    //{
    //  "title":"Bombom",
    //  "nickname":"",
    //  "language":"en",
    //  "folder_id":"0",
    //  "category":"",
    //  "question_count":0,
    //  "page_count":1,
    //  "response_count":0,
    //  "date_created":"2021-03-19T02:27:00",
    //  "date_modified":"2021-03-19T02:27:00",
    //  "id":"303427429",
    //  "buttons_text":
    //    {"next_button":"Next",
    //     "prev_button":"Prev",
    //     "done_button":"Done",
    //     "exit_button":"Exit"
    //    },
    //  "is_owner":true,
    //  "footer":true,
    //  "custom_variables":{},
    //  "href":"https:\/\/api.surveymonkey.com\/v3\/surveys\/303427429",
    //  "analyze_url":"https:\/\/www.surveymonkey.com\/analyze\/d5b6ZBgbVkYqM3tb1d62mZxt82N_2BdzwMbBWD0Cx2DvQ_3D",
    //  "edit_url":"https:\/\/www.surveymonkey.com\/create\/?sm=d5b6ZBgbVkYqM3tb1d62mZxt82N_2BdzwMbBWD0Cx2DvQ_3D",
    //  "collect_url":"https:\/\/www.surveymonkey.com\/collect\/list?sm=d5b6ZBgbVkYqM3tb1d62mZxt82N_2BdzwMbBWD0Cx2DvQ_3D",
    //  "summary_url":"https:\/\/www.surveymonkey.com\/summary\/d5b6ZBgbVkYqM3tb1d62mZxt82N_2BdzwMbBWD0Cx2DvQ_3D",
    //  "preview":"https:\/\/www.surveymonkey.com\/r\/Preview\/?sm=R5xRLDh1hevRGLLRF6ola0HSN6llmqgmCFevYLOL2L2segkK9UwgBeOX0rILTWQI"
    //}

    // Private fields
    // {
    private String id;
    private String title;
    private String nickname;
    private String href;
    private String language;
    private String folder_id;
    private String category;
    private String question_count;
    private String page_count;
    private String response_count;
    private String date_created;
    private String date_modified;
    private Object buttons_text;
    private String is_owner;
    private String footer;
    private Object quiz_options;
    private Object custom_variables;
    private String analyze_url;
    private String edit_url;
    private String collect_url;
    private String summary_url;
    private String preview;
    // }

    // getter and setter of private fields
    // {

    // ID
    private void setID(String id) {
        this.id = id;
    }

    public String getID() {
        return this.id;
    }

    // Title
    private void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    // Nickname
    private void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return this.nickname;
    }

    // Hyperlink
    private void setHref(String href) {
        this.href = href;
    }

    public String getHref() {
        return this.href;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFolder_id() {
        return folder_id;
    }

    public void setFolder_id(String folder_id) {
        this.folder_id = folder_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion_count() {
        return question_count;
    }

    public void setQuestion_count(String question_count) {
        this.question_count = question_count;
    }

    public String getPage_count() {
        return page_count;
    }

    public void setPage_count(String page_count) {
        this.page_count = page_count;
    }

    public String getResponse_count() {
        return response_count;
    }

    public void setResponse_count(String response_count) {
        this.response_count = response_count;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(String date_modified) {
        this.date_modified = date_modified;
    }

    public Object getButtons_text() {
        return buttons_text;
    }

    public void setButtons_text(Object buttons_text) {
        this.buttons_text = buttons_text;
    }

    public String getIs_owner() {
        return is_owner;
    }

    public void setIs_owner(String is_owner) {
        this.is_owner = is_owner;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public Object getQuiz_options() {
        return quiz_options;
    }

    public void setQuiz_options(Object quiz_options) {
        this.quiz_options = quiz_options;
    }

    public Object getCustom_variables() {
        return custom_variables;
    }

    public void setCustom_variables(Object custom_variables) {
        this.custom_variables = custom_variables;
    }

    public String getAnalyze_url() {
        return analyze_url;
    }

    public void setAnalyze_url(String analyze_url) {
        this.analyze_url = analyze_url;
    }

    public String getEdit_url() {
        return edit_url;
    }

    public void setEdit_url(String edit_url) {
        this.edit_url = edit_url;
    }

    public String getCollect_url() {
        return collect_url;
    }

    public void setCollect_url(String collect_url) {
        this.collect_url = collect_url;
    }

    public String getSummary_url() {
        return summary_url;
    }

    public void setSummary_url(String summary_url) {
        this.summary_url = summary_url;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }
    // }
}
