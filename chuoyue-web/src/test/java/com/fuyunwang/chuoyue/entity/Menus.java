package com.fuyunwang.chuoyue.entity;

import java.util.List;

/**
 * @description:
 * @author: FuyunWang
 * @time: 2020/9/25 21:38
 */
public class Menus {

    /**
     * data : [{"id":125,"authName":"用户管理","path":"users","children":[{"id":110,"authName":"用户列表","path":"users","children":[],"order":null}],"order":1},{"id":103,"authName":"权限管理","path":"rights","children":[{"id":111,"authName":"角色列表","path":"roles","children":[],"order":null},{"id":112,"authName":"权限列表","path":"rights","children":[],"order":null}],"order":2},{"id":101,"authName":"商品管理","path":"goods","children":[{"id":104,"authName":"商品列表","path":"goods","children":[],"order":1},{"id":115,"authName":"分类参数","path":"params","children":[],"order":2},{"id":121,"authName":"商品分类","path":"categories","children":[],"order":3}],"order":3},{"id":102,"authName":"订单管理","path":"orders","children":[{"id":107,"authName":"订单列表","path":"orders","children":[],"order":null}],"order":4},{"id":145,"authName":"数据统计","path":"reports","children":[{"id":146,"authName":"数据报表","path":"reports","children":[],"order":null}],"order":5}]
     * meta : {"msg":"获取菜单列表成功","status":200}
     */

    private MetaBean meta;
    private List<DataBean> data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class MetaBean {
        /**
         * msg : 获取菜单列表成功
         * status : 200
         */

        private String msg;
        private int status;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    public static class DataBean {
        /**
         * id : 125
         * authName : 用户管理
         * path : users
         * children : [{"id":110,"authName":"用户列表","path":"users","children":[],"order":null}]
         * order : 1
         */

        private int id;
        private String authName;
        private String path;
        private int order;
        private List<ChildrenBean> children;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAuthName() {
            return authName;
        }

        public void setAuthName(String authName) {
            this.authName = authName;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean {
            /**
             * id : 110
             * authName : 用户列表
             * path : users
             * children : []
             * order : null
             */

            private int id;
            private String authName;
            private String path;
            private Object order;
            private List<?> children;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAuthName() {
                return authName;
            }

            public void setAuthName(String authName) {
                this.authName = authName;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public Object getOrder() {
                return order;
            }

            public void setOrder(Object order) {
                this.order = order;
            }

            public List<?> getChildren() {
                return children;
            }

            public void setChildren(List<?> children) {
                this.children = children;
            }
        }
    }
}
