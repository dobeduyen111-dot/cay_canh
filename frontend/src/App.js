import { BrowserRouter, Routes, Route, Outlet } from "react-router-dom";
import Header from "./components/Header";
import Footer from "./components/Footer";

import Home from "./pages/Home";
import AllProducts from "./pages/AllProducts";
import ProductDetail from "./pages/ProductDetail";
import Cart from "./pages/Cart";
import Login from "./pages/Login";
import Register from "./pages/Register"; // Đã thêm

import AdminDashboard from "./pages/AdminDashboard";
import ProductManagement from "./pages/ProductManagement";
import AddProduct from "./pages/AddProduct";
import EditProduct from "./pages/EditProduct";

const PublicLayout = () => {
  return (
    <>
      <Header />
      <Outlet /> 
      <Footer />
    </>
  );
};

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route element={<PublicLayout />}>
          <Route path="/" element={<Home />} />
          <Route path="/product" element={<AllProducts />} />
          <Route path="/product/:id" element={<ProductDetail />} />
          <Route path="/cart" element={<Cart />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} /> {/* Đã thêm */}
        </Route>

        <Route path="/admin/dashboard" element={<AdminDashboard />} />
        <Route path="/admin/product" element={<ProductManagement />} />
        <Route path="/admin/product/add" element={<AddProduct />} />
        <Route path="/admin/product/edit/:id" element={<EditProduct />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;