package com.pl.schedule.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "LRMS_ACCESS_CONTROL_RECORD")
public class LrmsAccessControlRecord implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    private String            id;
    private String            sn;
    @Column(name = "record_index")
    private Long              recordIndex;
    @Column(name = "record_type")
    private Integer           recordType;
    @Column(name = "record_result")
    private Integer           recordResult;
    private Integer           doorno;
    private Integer           direction;
    private String            cardno;
    @Column(name = "record_accesstime")
    private Date              recordAccesstime;
    @Column(name = "record_errorcode")
    private Integer           recordErrorcode;
    @Column(name = "batch_id")
    private String            batchId;

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", recordIndex=").append(recordIndex);
        sb.append(", sn=").append(sn);
        sb.append(", cardno=").append(cardno);
        sb.append(", recordType=").append(recordType);
        sb.append(", recordResult=").append(recordResult);
        sb.append("]");
        return sb.toString();
    }

    public String getBatchId()
    {
        return batchId;
    }

    public void setBatchId(String batchId)
    {
        this.batchId = batchId;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getSn()
    {
        return sn;
    }

    public void setSn(String sn)
    {
        this.sn = sn;
    }

    public Long getRecordIndex()
    {
        return recordIndex;
    }

    public void setRecordIndex(Long recordIndex)
    {
        this.recordIndex = recordIndex;
    }

    public Integer getRecordType()
    {
        return recordType;
    }

    public void setRecordType(Integer recordType)
    {
        this.recordType = recordType;
    }

    public Integer getRecordResult()
    {
        return recordResult;
    }

    public void setRecordResult(Integer recordResult)
    {
        this.recordResult = recordResult;
    }

    public Integer getDoorno()
    {
        return doorno;
    }

    public void setDoorno(Integer doorno)
    {
        this.doorno = doorno;
    }

    public Integer getDirection()
    {
        return direction;
    }

    public void setDirection(Integer direction)
    {
        this.direction = direction;
    }

    public String getCardno()
    {
        return cardno;
    }

    public void setCardno(String cardno)
    {
        this.cardno = cardno;
    }

    public Date getRecordAccesstime()
    {
        return recordAccesstime;
    }

    public void setRecordAccesstime(Date recordAccesstime)
    {
        this.recordAccesstime = recordAccesstime;
    }

    public Integer getRecordErrorcode()
    {
        return recordErrorcode;
    }

    public void setRecordErrorcode(Integer recordErrorcode)
    {
        this.recordErrorcode = recordErrorcode;
    }
}
