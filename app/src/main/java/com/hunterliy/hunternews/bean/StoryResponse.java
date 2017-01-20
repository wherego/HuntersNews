package com.hunterliy.hunternews.bean;

import java.util.List;

public class StoryResponse extends JsonBean {
        private List<Story> stories;
        private String date;

        public StoryResponse(){
        }

        public void setStories(List<Story> stories) {
            this.stories = stories;
        }

        public List<Story> getStories() {
            return stories;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDate() {
            return date;
        }

        public boolean isEmpty(){
            if (stories==null)
                return true;
            return false;
        }

}
