package com.webapp.testweb;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
class RBean {
    private String name;    //input.htmlテンプレートにおいて、テキストボックスから名前入力を受け取るためのプロパティ。
    private String age; //ラジオボタンからの年齢の選択。'child'または'adult'

    private List<String> lang;  //チェックボックスからの開発経験の言語の選択。'Java','Python','JavaScript'から選択されたもののリストのデータを格納するプロパティ。
}
