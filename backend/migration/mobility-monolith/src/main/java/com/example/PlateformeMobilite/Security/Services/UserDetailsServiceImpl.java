// This file has been intentionally emptied.
// UserDetailsServiceImpl is no longer needed.
// The monolith does NOT authenticate users — it only validates JWT tokens.
// Roles are extracted directly from JWT claims in AuthTokenFilter.
// Keeping this @Service alive caused Spring to auto-configure an unnecessary
// AuthenticationManager, which is now eliminated.
