/*
 * Copyright (C) 2016 Nico(duyouhua1214@163.com), Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uni.common.image;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;

/**
 * Glide加载图片时请求与ImageView显示尺寸相匹配的加载器
 * <br><br>
 * Created by duyouhua on 16/8/28.
 */

public class SmartImageSizeUrlLoader extends BaseGlideUrlLoader<SmartImageSizeModel> {

    public SmartImageSizeUrlLoader(@NonNull Context context) {
        super(context);
    }

    /**
     * 封装CDN裁剪图片的url
     *
     * @param model  Glide加载图片时请求与ImageView显示尺寸相匹配的interface实现对象
     * @param width  ImageView的宽
     * @param height ImageView的高
     * @return
     */
    @Override
    protected String getUrl(@NonNull SmartImageSizeModel model, @IntRange(from = 1) int width, @IntRange(from = 1) int height) {
        return model.requestSmartSizeUrl(width, height);
    }
}
