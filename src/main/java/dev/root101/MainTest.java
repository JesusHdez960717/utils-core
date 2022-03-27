/*
 * Copyright 2021 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
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
package dev.root101;

import dev.root101.clean.core.app.repo.CRUDRepository;
import dev.root101.clean.core.domain.DomainObject;
import dev.root101.repo.memory.InMemoryCRUDRepo;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 */
public class MainTest {

    public static void main(String args[]) {
        CRUDRepository<Person> repo = new InMemoryCRUDRepo();

        repo.create(Person.build(1, "123"));

        Person p = Person.build(2, "hi");
        repo.create(p);

        System.out.println(repo.findAll());
        System.out.println(repo.count());

        Person p2 = Person.build(2, "hiiiiiiii");
        repo.edit(p2);

        System.out.println(repo.findAll());
        System.out.println(repo.count());
        
        
        repo.destroyById(1);

        System.out.println(repo.findAll());
        System.out.println(repo.count());
    }

    public static class Person extends DomainObject implements Comparable<Person> {

        public static Person build(int id, String name) {
            return new Person(id, name);
        }
        int id;
        String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public int compareTo(Person o) {
            return Integer.compare(id, o.id);
        }

        @Override
        public String toString() {
            return String.format("id: %s name: %s", id, name);
        }

    }
}
