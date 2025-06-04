'use client'

import Link from "next/link"
import Image from "next/image"
import { Button } from "@/components/ui/button"
import { DotLottieReact } from '@lottiefiles/dotlottie-react';

import {
  Zap,
  ArrowRight,
  Github,
  Linkedin,
  MapPin,
  Phone,
  Mail
} from "lucide-react"

export default function LandingPage() {
  return (
    <div className="flex min-h-screen flex-col bg-black text-white">
      {/* Header */}
      <header className="sticky top-0 z-50 w-full border-b border-gray-800 shadow-md backdrop-blur-md bg-black/80">
        <div className="container flex h-16 items-center justify-between px-4">
          <div className="flex items-center gap-2">
            <Zap className="h-8 w-8 text-blue-500" />
            <span className="text-2xl font-bold tracking-tight">Areeb Events</span>
          </div>
          <nav className="hidden md:flex items-center gap-6">
            <a href="#" className="hover:text-blue-400 transition">Events</a>
            <a href="https://www.areebtechnology.com/internship2025" className="relative after:absolute after:left-0 after:bottom-0 after:h-[2px] after:w-0 after:bg-blue-500 after:transition-all after:duration-300 hover:after:w-full">About</a>
            <a href="#contact" className="relative after:absolute after:left-0 after:bottom-0 after:h-[2px] after:w-0 after:bg-blue-500 after:transition-all after:duration-300 hover:after:w-full">Contact</a>
            <Link href="/auth/login" className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-lg transition-all shadow-sm hover:shadow-lg">Login</Link>
          </nav>
        </div>
      </header>

      {/* Hero Section */}
      <main className="flex-1 bg-gradient-to-b from-black via-blue-900/20 to-black">
        <section className="py-16 sm:py-20 lg:py-24">
          <div className="container px-4 sm:px-6 lg:px-8 grid gap-8 lg:grid-cols-2 items-center">
            <div className="space-y-8 max-w-xl mx-auto lg:mx-0">
              <Zap className="h-8 w-8 text-blue-500" />
              <div className="flex items-center gap-3">
                <h1 className="text-3xl sm:text-4xl md:text-5xl lg:text-6xl font-extrabold tracking-tight">
                  <p>Event Booking</p>
                  <span className="text-blue-400">Made Easy</span>
                </h1>
              </div>
              <p className="text-lg sm:text-xl text-gray-300 leading-relaxed">
                Plan, discover, and book events effortlessly from tech to entertainment, all in one place.
              </p>
              <div className="flex flex-col sm:flex-row gap-4">
                <Button
                  asChild
                  size="lg"
                  className="bg-gradient-to-r from-blue-600 to-blue-800 shadow-lg text-white px-6 py-3 text-base font-semibold hover:opacity-90 transition rounded-xl"
                >
                  <Link href="/auth/register" className="flex items-center">
                    Get Started <ArrowRight className="ml-2 h-5 w-5" />
                  </Link>
                </Button>
                <Button
                  size="lg"
                  variant="outline"
                  className="border-blue-500 text-blue-500 hover:bg-blue-500 hover:text-white px-6 py-3 text-base rounded-xl"
                >
                  <Link href="/auth/login">Login</Link>
                </Button>
              </div>
            </div>
            <div className="w-full flex justify-center md:justify-end px-4 py-6 relative group">
              {/* Tech grid background */}
              <div className="absolute inset-0 bg-[radial-gradient(circle,rgba(99,102,241,0.1)_1px,transparent_1px)] bg-[size:20px_20px] opacity-0 group-hover:opacity-100 transition-opacity duration-500 rounded-3xl z-0"></div>

              {/* Tech accent elements */}
              <div className="absolute -bottom-2 -right-2 w-16 h-16 border-r-2 border-b-2 border-indigo-500/30 rounded-br-3xl opacity-0 group-hover:opacity-100 transition-all duration-500 z-10"></div>
              <div className="absolute -top-2 -left-2 w-12 h-12 border-l-2 border-t-2 border-indigo-500/30 rounded-tl-3xl opacity-0 group-hover:opacity-100 transition-all duration-500 z-10"></div>

              {/* Image with enhanced styling */}
              <div className="relative overflow-hidden rounded-3xl z-20">
                {/* Gradient overlay */}
                <div className="absolute inset-0 bg-gradient-to-r from-indigo-500/10 via-purple-500/10 to-pink-500/10 opacity-0 group-hover:opacity-100 transition-all duration-500 z-10 pointer-events-none"></div>

                <Image
                  src="/undraw_date-picker_qe47.svg"
                  alt="Event Booking Animation"
                  width={550}
                  height={550}
                  className="
        rounded-3xl
        shadow-lg
        max-w-full
        h-auto
        object-contain
        transition-all duration-500 ease-out
        group-hover:scale-105
        group-hover:rotate-2
        group-hover:shadow-[0_0_25px_rgba(99,102,241,0.4)]
        cursor-pointer
        focus:outline-none
        focus:ring-4
        focus:ring-indigo-400
        focus:ring-opacity-50
        z-20
      "
                  priority
                  tabIndex={0}
                  role="img"
                  aria-label="Illustration of event booking"
                  style={{
                    animation: "float 6s ease-in-out infinite",
                  }}
                />

                {/* Diagonal tech overlay */}
                <div className="absolute inset-0 bg-[linear-gradient(45deg,transparent_95%,rgba(99,102,241,0.3)_95%),linear-gradient(135deg,transparent_95%,rgba(99,102,241,0.3)_95%)] bg-[size:20px_20px] opacity-0 group-hover:opacity-30 transition-opacity duration-700 pointer-events-none z-10"></div>
              </div>
            </div>
          </div>
        </section>
+
        {/* Features Section */}
        <section id="features" className="py-24 bg-[#0B0F19] text-center">
          <div className="container max-w-4xl mx-auto space-y-8">
            <h2 className="text-3xl sm:text-4xl font-bold text-white">
              Powerful Features for Booking Events
            </h2>
            <p className="text-gray-400 text-lg max-w-2xl mx-auto">
              Everything you need to attend; tech, educational, comedy, and more.
            </p>
          </div>
        </section>
      </main>

      {/* Footer */}
      <footer className="bg-black border-t border-gray-800 text-white py-16 px-4" id="contact">
        <div className="container grid grid-cols-1 md:grid-cols-3 gap-12">
          {/* Logo + Description */}
          <div>
            <div className="flex items-center gap-2 mb-4">
              <Zap className="h-6 w-6 text-blue-500" />
              <h2 className="text-xl font-bold">Areeb Events</h2>
            </div>
            <p className="text-sm text-gray-400 max-w-sm">
              Simplify your booking experience with our all-in-one platform.
            </p>
          </div>

          {/* Contact Information */}
          <div>
            <h3 className="text-lg font-semibold mb-4">Contact Me</h3>
            <ul className="space-y-3 text-sm text-gray-400">
              <li className="flex items-center gap-2">
                <Mail className="h-4 w-4 text-blue-500" />
                <a href="mailto:abdullah.abduljalil.zaky@gmail.com" className="hover:text-blue-400 transition">abdullah.abduljalil.zaky@gmail.com</a>
              </li>
              <li className="flex items-center gap-2">
                <MapPin className="h-4 w-4 text-blue-500" />
                <span>Cairo, Egypt</span>
              </li>
              <li className="flex items-center gap-2">
                <Phone className="h-4 w-4 text-blue-500" />
                <a href="tel:01151625051" className="hover:text-blue-400 transition">01151625051</a>
              </li>
            </ul>
          </div>

          {/* Social Links */}
          <div>
            <h3 className="text-lg font-semibold mb-4">Follow Me</h3>
            <div className="flex gap-4">
              <Link href="https://www.linkedin.com/in/abdullah-abdulgalil-aa583a285" target="_blank" className="hover:text-blue-400 transition">
                <Linkedin className="h-5 w-5" />
              </Link>
              <Link href="https://github.com/AbdullahAbdelglil" target="_blank" className="hover:text-blue-400 transition">
                <Github className="h-5 w-5" />
              </Link>
            </div>
          </div>
        </div>

        <div className="mt-12 border-t border-gray-700 pt-6 text-center text-sm text-gray-500">
          &copy; {new Date().getFullYear()} Areeb Events. All rights reserved.
        </div>
      </footer>
    </div>
  )
}
