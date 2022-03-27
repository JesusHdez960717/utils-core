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
 * @param <K>
 * @param <L>
 */
public class Trio<T, K, L> implements Serializable {

    private T A;
    private K B;
    private L C;

    public Trio() {
    }

    public Trio(T A, K B, L C) {
        this.A = A;
        this.B = B;
        this.C = C;
    }

    public T getA() {
        return A;
    }

    public void setA(T A) {
        this.A = A;
    }

    public K getB() {
        return B;
    }

    public void setB(K B) {
        this.B = B;
    }

    public L getC() {
        return C;
    }

    public void setC(L C) {
        this.C = C;
    }
}
