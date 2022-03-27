/*
 * Copyright 2022 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Or read it directly from LICENCE.txt file at the root of this project.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.root101.utils.others;

import java.io.Serializable;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 * @param <T>
 */
public class Pair<T> implements Serializable {

    private T A;
    private T B;

    public Pair() {
    }

    public Pair(T A, T B) {
        this.A = A;
        this.B = B;
    }

    public T getA() {
        return A;
    }

    public void setA(T A) {
        this.A = A;
    }

    public T getB() {
        return B;
    }

    public void setB(T B) {
        this.B = B;
    }
}
