import type { NextConfig } from "next";
import { createProxyMiddleware } from 'http-proxy-middleware';

const nextConfig: NextConfig = {
  async rewrites() {
    return [
      {
        source: '/api/:path*', // all requests to /api/* will be proxied
        destination: 'http://localhost"8081/api/:path*', // Your backend URL
      },
    ];
  },
};


export default nextConfig;
