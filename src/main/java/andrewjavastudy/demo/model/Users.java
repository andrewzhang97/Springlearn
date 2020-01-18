package andrewjavastudy.demo.model;

public class Users {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USERS.ID
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USERS.ACCOUNT_ID
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    private String accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USERS.NAME
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USERS.TOKEN
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USERS.GMT_CREATE
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USERS.GMT_MODIFIED
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USERS.BIO
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    private String bio;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USERS.AVATAR_URL
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    private String avatarUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USERS.ID
     *
     * @return the value of USERS.ID
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USERS.ID
     *
     * @param id the value for USERS.ID
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USERS.ACCOUNT_ID
     *
     * @return the value of USERS.ACCOUNT_ID
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USERS.ACCOUNT_ID
     *
     * @param accountId the value for USERS.ACCOUNT_ID
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USERS.NAME
     *
     * @return the value of USERS.NAME
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USERS.NAME
     *
     * @param name the value for USERS.NAME
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USERS.TOKEN
     *
     * @return the value of USERS.TOKEN
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USERS.TOKEN
     *
     * @param token the value for USERS.TOKEN
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USERS.GMT_CREATE
     *
     * @return the value of USERS.GMT_CREATE
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USERS.GMT_CREATE
     *
     * @param gmtCreate the value for USERS.GMT_CREATE
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USERS.GMT_MODIFIED
     *
     * @return the value of USERS.GMT_MODIFIED
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USERS.GMT_MODIFIED
     *
     * @param gmtModified the value for USERS.GMT_MODIFIED
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USERS.BIO
     *
     * @return the value of USERS.BIO
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    public String getBio() {
        return bio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USERS.BIO
     *
     * @param bio the value for USERS.BIO
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    public void setBio(String bio) {
        this.bio = bio == null ? null : bio.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USERS.AVATAR_URL
     *
     * @return the value of USERS.AVATAR_URL
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USERS.AVATAR_URL
     *
     * @param avatarUrl the value for USERS.AVATAR_URL
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }
}