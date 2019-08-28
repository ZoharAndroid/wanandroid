package com.zohar.wanandroid.bean.knowledge;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by zohar on 2019/8/27 13:33
 * Describe:
 *  Knowledge的data的子bean
 *  公众号的data 同bean
 */
public class SubKnowledge implements Parcelable {
    /**
     *        "children:[**]
     *       "courseId": 13,
     *       "id": 150,
     *       "name": "开发环境",
     *       "order": 1,
     *       "parentChapterId": 0,
     *       "userControlSetTop": false,
     *       "visible": 1
     */
    private List<SubKnowledge> children;
    private int courseId;
    private int id;
    private String name;
    private int order;
    private int parentChapterId;
    private boolean userControlSetTop;
    private int visible;


    protected SubKnowledge(Parcel in) {
        children = in.createTypedArrayList(SubKnowledge.CREATOR);
        courseId = in.readInt();
        id = in.readInt();
        name = in.readString();
        order = in.readInt();
        parentChapterId = in.readInt();
        userControlSetTop = in.readByte() != 0;
        visible = in.readInt();
    }

    public static final Creator<SubKnowledge> CREATOR = new Creator<SubKnowledge>() {
        @Override
        public SubKnowledge createFromParcel(Parcel in) {
            return new SubKnowledge(in);
        }

        @Override
        public SubKnowledge[] newArray(int size) {
            return new SubKnowledge[size];
        }
    };

    public List<SubKnowledge> getChildren() {
        return children;
    }

    public void setChildren(List<SubKnowledge> children) {
        this.children = children;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getParentChapterId() {
        return parentChapterId;
    }

    public void setParentChapterId(int parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public boolean isUserControlSetTop() {
        return userControlSetTop;
    }

    public void setUserControlSetTop(boolean userControlSetTop) {
        this.userControlSetTop = userControlSetTop;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "SubKnowledge{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(children);
        dest.writeInt(courseId);
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(order);
        dest.writeInt(parentChapterId);
        dest.writeByte((byte) (userControlSetTop ? 1 : 0));
        dest.writeInt(visible);
    }
}
