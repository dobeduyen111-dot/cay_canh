--
-- PostgreSQL database dump
--

\restrict eGxtv63aPHsB3pJnp3RNFtGL4VVxLns6SzBg7ubgFrPFaPqMzmVF9gjImQTyE5B

-- Dumped from database version 18.3 (Ubuntu 18.3-1.pgdg24.04+1)
-- Dumped by pg_dump version 18.3 (Ubuntu 18.3-1.pgdg24.04+1)

-- Started on 2026-04-04 10:48:28 UTC

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 248 (class 1255 OID 16564)
-- Name: sp_registeruser(character varying, character varying, character varying, character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.sp_registeruser(p_fullname character varying, p_email character varying, p_password character varying, p_phone character varying DEFAULT NULL::character varying, p_address character varying DEFAULT NULL::character varying, p_role character varying DEFAULT 'USER'::character varying) RETURNS TABLE(status integer, message character varying)
    LANGUAGE plpgsql
    AS $$
DECLARE
    v_NewUserId INT;
BEGIN
    IF EXISTS (SELECT 1 FROM Users WHERE Email = p_Email) THEN
        RETURN QUERY SELECT -1, 'Email đã tồn tại'::VARCHAR;
        RETURN;
    END IF;

    INSERT INTO Users (FullName, Email, Password, Phone, Address, Role)
    VALUES (p_FullName, p_Email, p_Password, p_Phone, p_Address, p_Role)
    RETURNING UserId INTO v_NewUserId;

    INSERT INTO Cart (UserId) VALUES (v_NewUserId);

    RETURN QUERY SELECT 1, 'Đăng ký thành công'::VARCHAR;
END;
$$;


ALTER FUNCTION public.sp_registeruser(p_fullname character varying, p_email character varying, p_password character varying, p_phone character varying, p_address character varying, p_role character varying) OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 226 (class 1259 OID 16619)
-- Name: cart; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cart (
    cartid integer NOT NULL,
    userid integer NOT NULL,
    updatedat timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.cart OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16618)
-- Name: cart_cartid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cart_cartid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cart_cartid_seq OWNER TO postgres;

--
-- TOC entry 3542 (class 0 OID 0)
-- Dependencies: 225
-- Name: cart_cartid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cart_cartid_seq OWNED BY public.cart.cartid;


--
-- TOC entry 228 (class 1259 OID 16634)
-- Name: cartitems; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cartitems (
    cartitemid integer NOT NULL,
    cartid integer NOT NULL,
    productid integer NOT NULL,
    quantity integer DEFAULT 1 NOT NULL,
    addedat timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.cartitems OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16633)
-- Name: cartitems_cartitemid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cartitems_cartitemid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cartitems_cartitemid_seq OWNER TO postgres;

--
-- TOC entry 3543 (class 0 OID 0)
-- Dependencies: 227
-- Name: cartitems_cartitemid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cartitems_cartitemid_seq OWNED BY public.cartitems.cartitemid;


--
-- TOC entry 222 (class 1259 OID 16586)
-- Name: categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categories (
    categoryid integer NOT NULL,
    categoryname character varying(100) NOT NULL,
    description text,
    icon character varying(100) DEFAULT 'plant-icon.png'::character varying
);


ALTER TABLE public.categories OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16585)
-- Name: categories_categoryid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.categories_categoryid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.categories_categoryid_seq OWNER TO postgres;

--
-- TOC entry 3544 (class 0 OID 0)
-- Dependencies: 221
-- Name: categories_categoryid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.categories_categoryid_seq OWNED BY public.categories.categoryid;


--
-- TOC entry 232 (class 1259 OID 16677)
-- Name: orderitems; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orderitems (
    orderitemid integer NOT NULL,
    orderid integer NOT NULL,
    productid integer NOT NULL,
    quantity integer NOT NULL,
    price numeric(18,2) NOT NULL
);


ALTER TABLE public.orderitems OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 16676)
-- Name: orderitems_orderitemid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orderitems_orderitemid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.orderitems_orderitemid_seq OWNER TO postgres;

--
-- TOC entry 3545 (class 0 OID 0)
-- Dependencies: 231
-- Name: orderitems_orderitemid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orderitems_orderitemid_seq OWNED BY public.orderitems.orderitemid;


--
-- TOC entry 230 (class 1259 OID 16657)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    orderid integer NOT NULL,
    userid integer NOT NULL,
    orderdate timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    status character varying(50) DEFAULT 'Chờ xử lý'::character varying,
    totalamount numeric(18,2) DEFAULT 0,
    shippingaddress character varying(255) NOT NULL,
    note text
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16656)
-- Name: orders_orderid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orders_orderid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.orders_orderid_seq OWNER TO postgres;

--
-- TOC entry 3546 (class 0 OID 0)
-- Dependencies: 229
-- Name: orders_orderid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_orderid_seq OWNED BY public.orders.orderid;


--
-- TOC entry 234 (class 1259 OID 16699)
-- Name: payments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.payments (
    paymentid integer NOT NULL,
    orderid integer NOT NULL,
    paymentmethod character varying(50) DEFAULT 'Tiền mặt'::character varying,
    amount numeric(18,2) NOT NULL,
    paymentdate timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    issuccessful boolean DEFAULT false,
    CONSTRAINT payments_paymentmethod_check CHECK (((paymentmethod)::text = ANY ((ARRAY['Tiền mặt'::character varying, 'Chuyển khoản'::character varying, 'Momo'::character varying, 'Thẻ tín dụng'::character varying])::text[])))
);


ALTER TABLE public.payments OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 16698)
-- Name: payments_paymentid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.payments_paymentid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.payments_paymentid_seq OWNER TO postgres;

--
-- TOC entry 3547 (class 0 OID 0)
-- Dependencies: 233
-- Name: payments_paymentid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.payments_paymentid_seq OWNED BY public.payments.paymentid;


--
-- TOC entry 224 (class 1259 OID 16598)
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    productid integer NOT NULL,
    categoryid integer NOT NULL,
    productname character varying(150) NOT NULL,
    description text,
    careguide text,
    price numeric(18,2) NOT NULL,
    stock integer DEFAULT 0,
    image character varying(255),
    isactive boolean DEFAULT true,
    createdat timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.products OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16597)
-- Name: products_productid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.products_productid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.products_productid_seq OWNER TO postgres;

--
-- TOC entry 3548 (class 0 OID 0)
-- Dependencies: 223
-- Name: products_productid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.products_productid_seq OWNED BY public.products.productid;


--
-- TOC entry 220 (class 1259 OID 16566)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    userid integer NOT NULL,
    fullname character varying(100) NOT NULL,
    email character varying(150) NOT NULL,
    password character varying(255) NOT NULL,
    phone character varying(20),
    address character varying(255),
    role character varying(20) DEFAULT 'USER'::character varying NOT NULL,
    enabled boolean DEFAULT true,
    createdat timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT users_role_check CHECK (((role)::text = ANY ((ARRAY['ADMIN'::character varying, 'USER'::character varying])::text[])))
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16565)
-- Name: users_userid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_userid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_userid_seq OWNER TO postgres;

--
-- TOC entry 3549 (class 0 OID 0)
-- Dependencies: 219
-- Name: users_userid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_userid_seq OWNED BY public.users.userid;


--
-- TOC entry 236 (class 1259 OID 16718)
-- Name: wishlist; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wishlist (
    wishlistid integer NOT NULL,
    userid integer NOT NULL,
    productid integer NOT NULL,
    addedat timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.wishlist OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 16717)
-- Name: wishlist_wishlistid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.wishlist_wishlistid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.wishlist_wishlistid_seq OWNER TO postgres;

--
-- TOC entry 3550 (class 0 OID 0)
-- Dependencies: 235
-- Name: wishlist_wishlistid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.wishlist_wishlistid_seq OWNED BY public.wishlist.wishlistid;


--
-- TOC entry 3322 (class 2604 OID 16622)
-- Name: cart cartid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart ALTER COLUMN cartid SET DEFAULT nextval('public.cart_cartid_seq'::regclass);


--
-- TOC entry 3324 (class 2604 OID 16637)
-- Name: cartitems cartitemid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cartitems ALTER COLUMN cartitemid SET DEFAULT nextval('public.cartitems_cartitemid_seq'::regclass);


--
-- TOC entry 3316 (class 2604 OID 16589)
-- Name: categories categoryid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories ALTER COLUMN categoryid SET DEFAULT nextval('public.categories_categoryid_seq'::regclass);


--
-- TOC entry 3331 (class 2604 OID 16680)
-- Name: orderitems orderitemid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderitems ALTER COLUMN orderitemid SET DEFAULT nextval('public.orderitems_orderitemid_seq'::regclass);


--
-- TOC entry 3327 (class 2604 OID 16660)
-- Name: orders orderid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN orderid SET DEFAULT nextval('public.orders_orderid_seq'::regclass);


--
-- TOC entry 3332 (class 2604 OID 16702)
-- Name: payments paymentid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payments ALTER COLUMN paymentid SET DEFAULT nextval('public.payments_paymentid_seq'::regclass);


--
-- TOC entry 3318 (class 2604 OID 16601)
-- Name: products productid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products ALTER COLUMN productid SET DEFAULT nextval('public.products_productid_seq'::regclass);


--
-- TOC entry 3312 (class 2604 OID 16569)
-- Name: users userid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN userid SET DEFAULT nextval('public.users_userid_seq'::regclass);


--
-- TOC entry 3336 (class 2604 OID 16721)
-- Name: wishlist wishlistid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlist ALTER COLUMN wishlistid SET DEFAULT nextval('public.wishlist_wishlistid_seq'::regclass);


--
-- TOC entry 3526 (class 0 OID 16619)
-- Dependencies: 226
-- Data for Name: cart; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cart (cartid, userid, updatedat) FROM stdin;
1	1	2026-04-03 06:33:19.679339
\.


--
-- TOC entry 3528 (class 0 OID 16634)
-- Dependencies: 228
-- Data for Name: cartitems; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cartitems (cartitemid, cartid, productid, quantity, addedat) FROM stdin;
\.


--
-- TOC entry 3522 (class 0 OID 16586)
-- Dependencies: 222
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categories (categoryid, categoryname, description, icon) FROM stdin;
1	Cây Cảnh	Các loại cây xanh trang trí, để bàn, nội thất	plant-icon.png
2	Chậu Trồng Cây	Chậu cảnh với nhiều kích thước và chất liệu	plant-icon.png
3	Phụ Kiện Chăm Sóc	Dụng cụ và vật liệu chăm sóc cây cảnh	plant-icon.png
\.


--
-- TOC entry 3532 (class 0 OID 16677)
-- Dependencies: 232
-- Data for Name: orderitems; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orderitems (orderitemid, orderid, productid, quantity, price) FROM stdin;
1	2	9	1	90000.00
2	3	13	1	35000.00
3	4	4	7	250000.00
\.


--
-- TOC entry 3530 (class 0 OID 16657)
-- Dependencies: 230
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orders (orderid, userid, orderdate, status, totalamount, shippingaddress, note) FROM stdin;
2	1	2026-04-03 06:38:31.533106	Đang giao	90000.00	123	1111
3	1	2026-04-03 11:59:35.320803	Chờ xử lý	35000.00	123 ly1 thuong kiet	33333
4	1	2026-04-04 09:11:08.851283	Chờ xử lý	1750000.00	123	asdfasfd
\.


--
-- TOC entry 3534 (class 0 OID 16699)
-- Dependencies: 234
-- Data for Name: payments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.payments (paymentid, orderid, paymentmethod, amount, paymentdate, issuccessful) FROM stdin;
1	2	Chuyển khoản	90000.00	2026-04-03 06:38:35.19731	t
2	3	Tiền mặt	35000.00	2026-04-03 11:59:36.496754	t
3	4	Chuyển khoản	1750000.00	2026-04-04 09:11:12.711251	t
\.


--
-- TOC entry 3524 (class 0 OID 16598)
-- Dependencies: 224
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.products (productid, categoryid, productname, description, careguide, price, stock, image, isactive, createdat) FROM stdin;
1	1	Cây Kim Tiền	Cây tài lộc phong thủy thu hút may mắn.	Đặt nơi sáng vừa, tưới 1 tuần/lần	150000.00	50	kimtien.jpg	t	2026-04-03 06:32:52.628397
2	1	Cây Lưỡi Hổ	Lọc không khí hiệu quả, dễ trồng.	Ít tưới nước, tránh úng	120000.00	40	luoiho.jpg	t	2026-04-03 06:32:52.628397
3	1	Cây Trầu Bà	Dễ chăm, sống tốt trong nhà.	Ánh sáng nhẹ, tưới khi đất khô	90000.00	35	trauba.jpg	t	2026-04-03 06:32:52.628397
4	1	Cây Bàng Singapore Mini	Dáng đẹp thích hợp trang trí nội thất.	Tránh ánh nắng trực tiếp	250000.00	20	bangsing.jpg	t	2026-04-03 06:32:52.628397
5	1	Sen Đá Nâu	Nhỏ gọn, dễ thương, để bàn.	Cần nắng sáng, tưới 10 ngày/lần	45000.00	100	sendanau.jpg	t	2026-04-03 06:32:52.628397
6	1	Xương Rồng Tai Thỏ	Độc đáo, dễ chăm sóc.	Nắng mạnh, tưới rất ít	60000.00	80	taitho.jpg	t	2026-04-03 06:32:52.628397
7	1	Cây Lan Ý	Lọc độc tố trong không khí.	Tưới vừa phải, tránh nắng gắt	130000.00	45	lany.jpg	t	2026-04-03 06:32:52.628397
8	2	Chậu Xi Măng Tròn	Bền chắc, phong cách tối giản.	Phù hợp cây để ngoài trời	80000.00	40	ximang1.jpg	t	2026-04-03 06:32:52.628397
9	2	Chậu Xi Măng Vuông	Đơn giản và hiện đại.	Sử dụng được trong nhà hoặc ngoài trời	90000.00	35	ximang2.jpg	t	2026-04-03 06:32:52.628397
10	2	Chậu Gốm Sứ Trắng	Phù hợp cây trang trí nội thất.	Chú ý tránh va đập	70000.00	50	gom1.jpg	t	2026-04-03 06:32:52.628397
11	3	Sỏi Trang Trí Trắng	Tạo độ thẩm mỹ cao cho chậu cây.	Rải lớp trên mặt đất	22000.00	100	soi1.jpg	t	2026-04-03 06:32:52.628397
12	3	Sỏi Màu Mix	Đa dạng màu sắc.	Rải trang trí hoặc thủy sinh	28000.00	80	soi2.jpg	t	2026-04-03 06:32:52.628397
13	3	Đất Tribat	Dinh dưỡng phù hợp nhiều loại cây.	Thay đất định kỳ 6 tháng	35000.00	90	dat1.jpg	t	2026-04-03 06:32:52.628397
14	1	Cây Trầu Bà Vàng	Màu lá đẹp, phát triển nhanh.	Ánh sáng gián tiếp	95000.00	50	traubavang.jpg	t	2026-04-03 06:32:52.628397
15	1	Cây Phát Lộc	Phong thủy may mắn cho gia chủ.	Thay nước mỗi tuần	70000.00	60	phatloc.jpg	t	2026-04-03 06:32:52.628397
16	1	Cây Xương Rồng Trứng Chim	Xinh xắn phù hợp để bàn.	Nắng nhiều, rất ít tưới	50000.00	75	trungchim.jpg	t	2026-04-03 06:32:52.628397
17	1	Cây Cau Tiểu Trâm	Cây để bàn hút khí độc.	Tưới đều đặn, không để ngập úng	160000.00	40	cautieutram.jpg	t	2026-04-03 06:32:52.628397
18	1	Cây Trúc Nhật	Thanh lọc không khí tốt.	Ánh sáng nhẹ, tưới 2-3 lần/tuần	110000.00	35	trucnhat.jpg	t	2026-04-03 06:32:52.628397
19	1	Cây Hạnh Phúc Mini	Tượng trưng cho sự hạnh phúc viên mãn.	Ánh sáng nhẹ, tưới khi đất khô	180000.00	25	hanhphuc.jpg	t	2026-04-03 06:32:52.628397
20	1	Cây Cọ Nhật	Tạo điểm nhấn sang trọng trong phòng.	Tránh phơi nắng gắt	200000.00	20	conhat.jpg	t	2026-04-03 06:32:52.628397
21	1	Cây Vạn Lộc	Màu sắc rực rỡ mang lại may mắn.	Tưới 2 lần/tuần, đặt nơi thoáng mát	150000.00	30	vanloc.jpg	t	2026-04-03 06:32:52.628397
22	2	Chậu Gốm Họa Tiết	Họa tiết thủ công đẹp mắt.	Lau sạch bụi thường xuyên	95000.00	30	gom2.jpg	t	2026-04-03 06:32:52.628397
23	2	Chậu Nhựa Đen	Nhẹ và bền.	Phù hợp ươm cây	20000.00	100	nhua1.jpg	t	2026-04-03 06:32:52.628397
24	2	Chậu Treo Mini	Trang trí ban công.	Không treo nơi gió mạnh	35000.00	60	treo1.jpg	t	2026-04-03 06:32:52.628397
25	2	Chậu Sứ Để Bàn	Màu sắc tinh tế.	Khuyến khích trồng cây nhỏ	55000.00	70	su1.jpg	t	2026-04-03 06:32:52.628397
26	2	Chậu Gỗ Rustic	Phong cách mộc mạc vintage.	Tránh tiếp xúc nước kéo dài	150000.00	20	go1.jpg	t	2026-04-03 06:32:52.628397
27	2	Chậu Xi Măng Vân Đá	Thẩm mỹ cao, bền bỉ.	Phù hợp cây cảnh cỡ trung	110000.00	25	ximang3.jpg	t	2026-04-03 06:32:52.628397
28	2	Chậu Đá Nhẹ	Nhẹ nhưng cứng cáp.	Dễ di chuyển	120000.00	18	danhe.jpg	t	2026-04-03 06:32:52.628397
29	2	Chậu Sứ Vân Mây	Tạo điểm nhấn sang trọng.	Dễ vệ sinh	85000.00	40	vanmay.jpg	t	2026-04-03 06:32:52.628397
30	2	Chậu Nhựa Trắng Trơn	Sạch đẹp, bền.	Thích hợp trồng cây để bàn	25000.00	90	nhuatrang.jpg	t	2026-04-03 06:32:52.628397
31	2	Chậu Gốm Men Xanh	Màu xanh bắt mắt.	Tránh rơi vỡ	98000.00	28	gomxanh.jpg	t	2026-04-03 06:32:52.628397
32	2	Chậu Treo Sứ	Dành cho cây rủ.	Treo nơi thoáng mát	60000.00	35	treosuxinh.jpg	t	2026-04-03 06:32:52.628397
33	2	Chậu Composite Tròn Cao	Chống thấm tốt, sang trọng.	Dùng cho cây cao	180000.00	15	composite.jpg	t	2026-04-03 06:32:52.628397
34	3	Phân Bón Tan Chậm	Cung cấp dưỡng chất lâu dài.	Rải đều quanh gốc cây	30000.00	70	phan1.jpg	t	2026-04-03 06:32:52.628397
35	3	Bình Tưới Nhỏ	Tưới dễ dàng, tiện lợi.	Vệ sinh thường xuyên	45000.00	45	binhtuoi.jpg	t	2026-04-03 06:32:52.628397
36	3	Kéo Cắt Cành	Giúp tỉa cành gọn gàng.	Lau khô sau khi dùng	65000.00	30	keo.jpg	t	2026-04-03 06:32:52.628397
37	3	Dụng Cụ Xới Đất Mini	Hỗ trợ làm tơi đất.	Bảo quản nơi khô ráo	60000.00	40	xoidat.jpg	t	2026-04-03 06:32:52.628397
38	3	Than Hoạt Tính Trồng Cây	Khử mùi, chống nấm.	Trộn với đất	25000.00	55	thanhoat.jpg	t	2026-04-03 06:32:52.628397
39	3	Bột Dưỡng Rễ	Giúp cây phát triển rễ nhanh.	Sử dụng khi giâm cành	30000.00	30	duongre.jpg	t	2026-04-03 06:32:52.628397
40	3	Giá Đỡ Chậu Cây	Nâng chậu tạo bố cục đẹp.	Dùng cho chậu trung – lớn	120000.00	25	giado.jpg	t	2026-04-03 06:32:52.628397
41	3	Bình Xịt Phun Sương	Giữ ẩm cho lá.	Dùng mỗi ngày cho cây trong nhà	40000.00	60	phunsuong.jpg	t	2026-04-03 06:32:52.628397
42	3	Dụng Cụ Trồng Cây Mini 3 Món	Hỗ trợ trồng và chăm cây.	Lau sạch khi không dùng	85000.00	45	toolset.jpg	t	2026-04-03 06:32:52.628397
43	3	Lưới Lót Chậu	Ngăn đất trôi ra ngoài.	Đặt dưới đáy chậu trước khi trồng	15000.00	90	luichau.jpg	t	2026-04-03 06:32:52.628397
44	3	Găng Tay Làm Vườn	Chống trầy xước khi chăm cây.	Giặt sau mỗi lần dùng	25000.00	70	gangtay.jpg	t	2026-04-03 06:32:52.628397
45	3	Đèn Chiếu Cây Cảnh	Hỗ trợ ánh sáng cho cây trong nhà.	Bật 6–8 giờ/ngày	180000.00	15	dencay.jpg	t	2026-04-03 06:32:52.628397
\.


--
-- TOC entry 3520 (class 0 OID 16566)
-- Dependencies: 220
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (userid, fullname, email, password, phone, address, role, enabled, createdat) FROM stdin;
1	Admin	baohuynhgiasvien@gmail.com	123	\N	\N	ADMIN	t	2026-04-03 06:32:52.628397
2	User 1	huynhgiabao24112005@gmail.com	2411	\N	\N	USER	t	2026-04-03 06:32:52.628397
\.


--
-- TOC entry 3536 (class 0 OID 16718)
-- Dependencies: 236
-- Data for Name: wishlist; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.wishlist (wishlistid, userid, productid, addedat) FROM stdin;
\.


--
-- TOC entry 3551 (class 0 OID 0)
-- Dependencies: 225
-- Name: cart_cartid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cart_cartid_seq', 1, true);


--
-- TOC entry 3552 (class 0 OID 0)
-- Dependencies: 227
-- Name: cartitems_cartitemid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cartitems_cartitemid_seq', 3, true);


--
-- TOC entry 3553 (class 0 OID 0)
-- Dependencies: 221
-- Name: categories_categoryid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categories_categoryid_seq', 3, true);


--
-- TOC entry 3554 (class 0 OID 0)
-- Dependencies: 231
-- Name: orderitems_orderitemid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orderitems_orderitemid_seq', 3, true);


--
-- TOC entry 3555 (class 0 OID 0)
-- Dependencies: 229
-- Name: orders_orderid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_orderid_seq', 4, true);


--
-- TOC entry 3556 (class 0 OID 0)
-- Dependencies: 233
-- Name: payments_paymentid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.payments_paymentid_seq', 3, true);


--
-- TOC entry 3557 (class 0 OID 0)
-- Dependencies: 223
-- Name: products_productid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.products_productid_seq', 45, true);


--
-- TOC entry 3558 (class 0 OID 0)
-- Dependencies: 219
-- Name: users_userid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_userid_seq', 2, true);


--
-- TOC entry 3559 (class 0 OID 0)
-- Dependencies: 235
-- Name: wishlist_wishlistid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.wishlist_wishlistid_seq', 1, false);


--
-- TOC entry 3349 (class 2606 OID 16627)
-- Name: cart cart_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT cart_pkey PRIMARY KEY (cartid);


--
-- TOC entry 3351 (class 2606 OID 16645)
-- Name: cartitems cartitems_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cartitems
    ADD CONSTRAINT cartitems_pkey PRIMARY KEY (cartitemid);


--
-- TOC entry 3345 (class 2606 OID 16596)
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (categoryid);


--
-- TOC entry 3355 (class 2606 OID 16687)
-- Name: orderitems orderitems_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderitems
    ADD CONSTRAINT orderitems_pkey PRIMARY KEY (orderitemid);


--
-- TOC entry 3353 (class 2606 OID 16670)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (orderid);


--
-- TOC entry 3357 (class 2606 OID 16711)
-- Name: payments payments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payments
    ADD CONSTRAINT payments_pkey PRIMARY KEY (paymentid);


--
-- TOC entry 3347 (class 2606 OID 16612)
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (productid);


--
-- TOC entry 3341 (class 2606 OID 16584)
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- TOC entry 3343 (class 2606 OID 16582)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (userid);


--
-- TOC entry 3359 (class 2606 OID 16727)
-- Name: wishlist wishlist_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT wishlist_pkey PRIMARY KEY (wishlistid);


--
-- TOC entry 3361 (class 2606 OID 16729)
-- Name: wishlist wishlist_userid_productid_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT wishlist_userid_productid_key UNIQUE (userid, productid);


--
-- TOC entry 3363 (class 2606 OID 16628)
-- Name: cart cart_userid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT cart_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(userid) ON DELETE CASCADE;


--
-- TOC entry 3364 (class 2606 OID 16646)
-- Name: cartitems cartitems_cartid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cartitems
    ADD CONSTRAINT cartitems_cartid_fkey FOREIGN KEY (cartid) REFERENCES public.cart(cartid) ON DELETE CASCADE;


--
-- TOC entry 3365 (class 2606 OID 16651)
-- Name: cartitems cartitems_productid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cartitems
    ADD CONSTRAINT cartitems_productid_fkey FOREIGN KEY (productid) REFERENCES public.products(productid);


--
-- TOC entry 3367 (class 2606 OID 16688)
-- Name: orderitems orderitems_orderid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderitems
    ADD CONSTRAINT orderitems_orderid_fkey FOREIGN KEY (orderid) REFERENCES public.orders(orderid) ON DELETE CASCADE;


--
-- TOC entry 3368 (class 2606 OID 16693)
-- Name: orderitems orderitems_productid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderitems
    ADD CONSTRAINT orderitems_productid_fkey FOREIGN KEY (productid) REFERENCES public.products(productid);


--
-- TOC entry 3366 (class 2606 OID 16671)
-- Name: orders orders_userid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(userid);


--
-- TOC entry 3369 (class 2606 OID 16712)
-- Name: payments payments_orderid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payments
    ADD CONSTRAINT payments_orderid_fkey FOREIGN KEY (orderid) REFERENCES public.orders(orderid) ON DELETE CASCADE;


--
-- TOC entry 3362 (class 2606 OID 16613)
-- Name: products products_categoryid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_categoryid_fkey FOREIGN KEY (categoryid) REFERENCES public.categories(categoryid);


--
-- TOC entry 3370 (class 2606 OID 16735)
-- Name: wishlist wishlist_productid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT wishlist_productid_fkey FOREIGN KEY (productid) REFERENCES public.products(productid) ON DELETE CASCADE;


--
-- TOC entry 3371 (class 2606 OID 16730)
-- Name: wishlist wishlist_userid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT wishlist_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(userid) ON DELETE CASCADE;


-- Completed on 2026-04-04 10:48:29 UTC

--
-- PostgreSQL database dump complete
--

\unrestrict eGxtv63aPHsB3pJnp3RNFtGL4VVxLns6SzBg7ubgFrPFaPqMzmVF9gjImQTyE5B

